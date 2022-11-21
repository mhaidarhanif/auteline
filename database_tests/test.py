#! /usr/bin/python3

from dataclasses import dataclass
import mysql.connector
import os
import time
from typing import Any
import threading
from datetime import timedelta, datetime


DATABASE_NAME = "auteline"
TABLE_NAME = "bank_accounts"

# Tuples of account number, pin
TEST_ACCOUNTS = [
    (1110 + i, i)
    for i in range(10)
]

AVAILABLE_BALANCE = 1000.00
TOTAL_BALANCE = AVAILABLE_BALANCE + 200

SETUP_COMMANDS = \
    f"""
    DROP TABLE IF EXISTS {TABLE_NAME};

    CREATE TABLE {TABLE_NAME}(
        account_number int signed NOT NULL UNIQUE,
        pin int NOT NULL,
        available_balance double NOT NULL,
        total_balance double NOT NULL,
        PRIMARY KEY (account_number)
    );

    INSERT INTO {DATABASE_NAME}.{TABLE_NAME} (
        account_number,
        pin,
        available_balance,
        total_balance
    )
    VALUES
    {
        ",".join
        (
            f"({account}, {pin}, {AVAILABLE_BALANCE}, {TOTAL_BALANCE})"
            for account, pin in TEST_ACCOUNTS
        )
    };

    SELECT * FROM {TABLE_NAME};
    """

def setup():
    database_connection = mysql.connector.connect(
        host="100.115.82.243",
        user="sqiii",
        password=os.environ.get("SQL_PASSWORD"),
        database=DATABASE_NAME,
        autocommit=True
    )

    database_connection.start_transaction()
    cursor = database_connection.cursor()
    cursor.execute(SETUP_COMMANDS)
    cursor.fetchall()
    cursor.close()
    database_connection.close()



@dataclass
class SQL_COMMANDS:
    """Stores SQL commands"""
    
    DATABASE_CONNECTION: mysql.connector.MySQLConnection
    CURSOR: Any
    
    
    def individual_test_invalid_pin(self, account_index):
        account_num, pin = TEST_ACCOUNTS[account_index]

        INCORRECT_PIN_SQL = \
            f"""
            SELECT * FROM {TABLE_NAME}
            WHERE account_number = {account_num}
            AND pin = {pin + 1};
            """
        self.CURSOR.execute(INCORRECT_PIN_SQL)
        assert len(self.CURSOR.fetchall()) == 0
    

    def individual_test_valid_pin(self, account_index):
        account_num, pin = TEST_ACCOUNTS[account_index]

        CORRECT_PIN_SQL = \
            f"""
            SELECT * FROM {TABLE_NAME}
            WHERE account_number = {account_num}
            AND pin = {pin};
            """
        self.CURSOR.execute(CORRECT_PIN_SQL)
        results = self.CURSOR.fetchall()
        assert len(results) == 1


    def individual_test_get_balance(self, account_index):
        account_num, pin = TEST_ACCOUNTS[account_index]

        GET_BALANCE_SQL = \
            f"""
            SELECT available_balance FROM {TABLE_NAME}
            WHERE account_number = {account_num}
            AND pin = {pin}
            """
        

        self.CURSOR.execute(GET_BALANCE_SQL)
        results = self.CURSOR.fetchall()
        assert len(results) == 1
        assert results[0][0] == AVAILABLE_BALANCE

    def individual_test_deposit(self, account_index):
        account_num, pin = TEST_ACCOUNTS[account_index]

        DEPOSIT_SQL = \
            f"""
            UPDATE bank_accounts
            SET total_balance = {TOTAL_BALANCE + 350.00}
            WHERE account_number = {account_num}
            AND pin = {pin}
            """
        
        self.CURSOR.execute(DEPOSIT_SQL)
        self.DATABASE_CONNECTION.commit()
    
    def individual_test_withdrawal(self, account_index):
        account_num, pin = TEST_ACCOUNTS[account_index]

        WITHDRAWAL_SQL = \
            f"""
            UPDATE bank_accounts
            SET total_balance = {TOTAL_BALANCE - 100.00}, available_balance = {AVAILABLE_BALANCE - 100.00}
            WHERE account_number = {account_num}
            AND pin = {pin}
            """
        
        self.CURSOR.execute(WITHDRAWAL_SQL)
        self.DATABASE_CONNECTION.commit()


    def individual_test(self, account_index):
        self.individual_test_invalid_pin(account_index)
        self.individual_test_valid_pin(account_index)
        self.individual_test_get_balance(account_index)
        self.individual_test_deposit(account_index)
        self.individual_test_withdrawal(account_index)


def test_wrapper(account_index, ctx, con):
    MAX_DURATION = timedelta(seconds=30)

    start = datetime.now()
    tests = SQL_COMMANDS(DATABASE_CONNECTION=ctx, CURSOR=con)
    tests.individual_test(account_index)
    end = datetime.now()

    test_duration = end - start

    assert test_duration < MAX_DURATION

if __name__ == "__main__":
    setup()
    time.sleep(5)

    connections = [
        mysql.connector.connect(
            user="sqiii",
            password=os.environ.get("SQL_PASSWORD"),
            host="100.115.82.243",
            database=DATABASE_NAME,
            autocommit = True
        ) for i in range(10)
    ]


    threads = []
    for i in range(10):
        t = threading.Thread(target=test_wrapper, args=(i, connections[i], connections[i].cursor()))
        threads.append(t)
        t.start()


    [t.join() for t in threads]