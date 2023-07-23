How to setup this in your machine:

1. Clone the project.
2. Run the database/RunThisForFirstTime.sql in your SQL Server Studio if this is your first time doing this, 
    run database/RunThisIfHasThisDatabaseBefore.sql if you has create this database before.
3. Change the config in DBConnect.java (in src/java/dao/impl) to your database port and password.
4. Run the project, the testing account for tourist is tourist_demo with password 123456, for travel agent
    is agent_demo and for admin is admin_demo with the same password.