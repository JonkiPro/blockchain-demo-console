# blockchain-demo-console
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

* The application simulates the operation of a blockchain network. The network allows the transfer of data and cryptocurrencies between network users identified by unique addresses. Each transaction is placed in the last extracted block.
* Each new block is assigned a unique identifying hash generated from its metadata.
* Also, each transaction has a unique identifying hash generated from its metadata.
* Each new block is created every (approximately) 1 second.
* Each new transaction is automatically placed in the last extracted block.
* For example, I created a blockchain interpretation for the bitcoin cryptocurrency in the [blockchain-bitcoin] module.

## Initial startup.
* The first time you run it, an SQL script [create_tables.sql] will be automatically executed to create the tables in the database. Tables are needed for the application to work properly.
* First in the console, you will see the main menu where you can choose which blockchain you want to use. For the example, I created the <b>Blockchain Bitcoin</b>.
* When this option is selected, a seed (24 words) will be generated from a text file [words.txt]. Based on this seed, a private key will be generated, and based on the private key, a public key will be generated, which will be a public identifying address on the blockchain network to identify the wallet. The algorithm used is SH2-256.
* Seed, private key and public key will be saved in the file <b>locale_user.txt</b> in the folder <b>blockchain-bitcoin/src/main/resources/locale_user.txt</b>
* The local user will receive 100 BTC to test the network operation. 
* The application will initiate some sample addresses with a wallet balance of 100 BTC.
* Application ready to be tested.
## Preconditions
*  Java 17
*  MySQL
*  IDE for example IntelliJ
### MySQL
*  Create a user with all permissions. <b>username:</b> <i>root</i>, <b>password:</b> <i>PasswordRoot1</i>
*  Create a schema. <b>name:</b> <i>database-blockchain</i>, <b>owner:</b> <i>root</i>
*  All necessary tables will be created automatically during the first run of the application by executing SQL code from a file [create_tables.sql].
## Tech Stack
* Java 17
* JDBC
* MySQL
* Maven

## Screenshots
* Generated seed and keys. Main menu.
![SS1](https://user-images.githubusercontent.com/26285741/137776238-cdba6d9b-b224-44d5-acf2-fd7db724d237.png)
* List of mined blocks.
![SS2](https://user-images.githubusercontent.com/26285741/137776240-d79516b8-7f11-4deb-a250-7b2f146f0f57.png)
* Executing transactions.
![SS3](https://user-images.githubusercontent.com/26285741/137779386-969823ea-c567-4734-a0b3-97e217917f6a.png)
* List of available addresses.
![SS4](https://user-images.githubusercontent.com/26285741/137776245-4e5acdca-9b7e-4768-b088-7ee8267ac4b8.png)
* A block containing one transaction.
![SS5](https://user-images.githubusercontent.com/26285741/137776246-89ca9461-9b7b-419c-88b4-0d4a95c8ec1d.png)
* List of transactions.
![SS6](https://user-images.githubusercontent.com/26285741/137776247-6ea69b57-2c23-41cf-a36b-210cfb070117.png)


[create_tables.sql]: <https://github.com/JonkiPro/blockchain-demo-console/blob/main/blockchain-bitcoin/src/main/resources/create_tables.sql>
[words.txt]: <https://github.com/JonkiPro/blockchain-demo-console/blob/main/core/src/main/resources/words.txt>
[blockchain-bitcoin]: <https://github.com/JonkiPro/blockchain-demo-console/tree/main/blockchain-bitcoin>
