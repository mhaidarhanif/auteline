#! /usr/bin/python3

from dataclasses import dataclass
import mysql.connector
import os

DATABASE_NAME = "auteline"
TABLE_NAME = "bank_accounts"



# Tuples of account number, pin
TEST_ACCOUNTS = [
    (1110 + i, i)
    for i in range(10)
]

@dataclass
class SQL_COMMANDS:
    """Stores SQL commands"""

    SETUP_COMMANDS = \
        f"""
        DROP TABLE IF EXISTS {TABLE_NAME};

        CREATE TABLE `{TABLE_NAME}`(
            `account_number` int signed NOT NULL UNIQUE,
            `pin` int NOT NULL,
            `available_balance` double NOT NULL,
            `total_balance` double NOT NULL,
            PRIMARY KEY (`account_number`)
        );

        INSERT INTO `{DATABASE_NAME}`.`{TABLE_NAME}` (
            `account_number`,
            `pin`,
            `available_balance`,
            `total_balance`
        )
        VALUES
        {
            ",".join
            (
                f"({account}, {pin}, 1000.00, 1200.00)"
                for account, pin in TEST_ACCOUNTS
            )
        };
        """
    def get_cursor(self):
        return self.database_connection.cursor()
    
    def connect(self, host="sqiii-mysql", username="sqiii", password = os.environ.get("SQL_PASSWORD")):
        self.database_connection = mysql.connector.connect(
            host=host,
            user=username,
            password=password,
            database=DATABASE_NAME
        )

    
    def setup(self):
        cursor = self.get_cursor()

        cursor.execute(self.SETUP_COMMANDS)
    

    def indvidual_test(self, account_index):
        account_num, pin = TEST_ACCOUNTS[account_index]

        INCORRECT_PIN_SQL = \
            f"""
            SELECT * FROM `{TABLE_NAME}`
            WHERE `account_number` == {account_num}
            AND `pin` == {pin}
            """
        
        cursor = self.get_cursor()

        cursor.execute(INCORRECT_PIN_SQL)
        assert len(cursor.fetchall()) == 0

        cursor = self.get_cursor()
        
