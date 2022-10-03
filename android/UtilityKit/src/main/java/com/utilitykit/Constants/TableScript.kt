package com.utilitykit.Constants

class TableScript {

    companion object{
        val createMessageTable = """
                                            CREATE TABLE IF NOT EXISTS `Message` (
                                            `MessageID` int NOT NULL,
                                            `_id` tinytext ,
                                            `ConversationID` int(11) NOT NULL,
                                            `FromUserID` tinytext NOT NULL,
                                            `ToUserID` tinytext NOT NULL,
                                            `Content` tinytext NOT NULL,
                                            `ContentType` tinytext NOT NULL,
                                            `IsUploaded` tinyint DEFAULT 0,
                                            `IsDownloaded` tinyint DEFAULT 0,
                                            `IsDeleted` BOOLEAN DEFAULT 0,
                                            `IsSent` tinyint DEFAULT 0,
                                            `IsReceived` tinyint DEFAULT 0,
                                            `IsRead` tinyint DEFAULT 0,
                                            `SentTime` tinytext,
                                            `DeliveredTime` tinytext,
                                            `ReadTime` tinytext,
                                             PRIMARY KEY (MessageID)
                                            )
                                            """

        val createConversationTable = """
                                                CREATE TABLE IF NOT EXISTS `Conversation` (
                                                `_id` tinytext NOT NULL PRIMARY KEY,
                                                `FromUserID` tinytext NOT NULL,
                                                `ToUserID` tinytext NOT NULL,
                                                `OwnerUserID` text,
                                                `AdminUsers` text,
                                                `Participants` text,
                                                `AdminUserProfiles` text,
                                                `ParticipantProfiles` text,
                                                `ISGroup` text,
                                                `GroupName` text,
                                                `ProfilePicture` tinytext,
                                                `GroupDescription` text,
                                                `StatusDescription` text,
                                                `LastMessage` text,
                                                `Message` text ,
                                                `Profiles` text ,
                                                `CreatedAt` tinytext NOT NULL,
                                                `UpdatedAt` tinytext NOT NULL
                                                )
                                                """
        val createContactsTable = """
                                                CREATE TABLE IF NOT EXISTS `Contacts` (
                                                `ID` int(11),
                                                `UserID` tinytext,
                                                `_id` tinytext,
                                                `Name` text ,
                                                `MobileNumber` tinytext NOT NULL PRIMARY KEY,
                                                `CountryCode` tinytext,
                                                `ProfilePicture` text ,
                                                `Profile` text ,
                                                `Category` tinytext NOT NULL ,
                                                `CreatedAt` tinytext NOT NULL,
                                                `UpdatedAt` tinytext NOT NULL
                                                )
                                                """
        val createProfilesTable = """
                                            CREATE TABLE IF NOT EXISTS `Profile` (
                                            `_id` tinytext NOT NULL PRIMARY KEY,
                                            `UserID` tinytext NOT NULL,
                                            `Name` tinytext NOT NULL,
                                            `Gender` tinytext,
                                            `CountryCode` tinytext,
                                            `Status` tinytext,
                                            `EmailID` tinytext ,
                                            `MobileNumber` tinytext,
                                            `ProfilePicture` tinytext,
                                            `FCMToken` tinytext,
                                            `LastSeen` double
                                            )
                                            """


        val createFriendsTable = """
                                            CREATE TABLE IF NOT EXISTS `Friends` (
                                            `_id` tinytext NOT NULL PRIMARY KEY,
                                            `FromUserID` tinytext NOT NULL,
                                            `ToUserID` tinytext NOT NULL,
                                            `SentTime` tinytext,
                                            `DeliveredTime` tinytext,
                                            `ReadTime` tinytext,
                                            `AcceptedTime` tinytext,
                                            `Profile` text
                                            )
                                            """
        val createfriendRequestTable = """
                                            CREATE TABLE IF NOT EXISTS `FriendRequest` (
                                            `_id` tinytext NOT NULL PRIMARY KEY,
                                            `FromUserID` tinytext NOT NULL,
                                            `ToUserID` tinytext NOT NULL,
                                            `IsAccepted` tinytext ,
                                            `SentTime` tinytext,
                                            `DeliveredTime` tinytext,
                                            `ReadTime` tinytext,
                                            `AcceptedTime` tinytext,
                                            `Profile` text
                                            )
                                            """
        val createBlockListTable =  """
                                            CREATE TABLE IF NOT EXISTS `BlockList` (
                                            `_id` tinytext NOT NULL PRIMARY KEY,
                                            `UserID` tinytext NOT NULL,
                                            `Name` tinytext NOT NULL,
                                            `Gender` tinytext,
                                            `CountryCode` tinytext,
                                            `Status` tinytext,
                                            `EmailID` tinytext ,
                                            `MobileNumber` tinytext,
                                            `ProfilePicture` tinytext,
                                            `FCMToken` tinytext,
                                            `LastSeen` double
                                            )
                                            """
        val createFilesTable =  """
                                            CREATE TABLE IF NOT EXISTS `Files` (
                                            `RemoteURL` tinytext NOT NULL,
                                            `LocalURL` tinytext NOT NULL
                                            )
                                            """
        val createTaskTable =  """
                                            CREATE TABLE IF NOT EXISTS `Task` (
                                            `_id` tinytext NOT NULL PRIMARY KEY,
                                            `Name` tinytext NOT NULL,
                                            `Description` tinytext NOT NULL,
                                            `Priority` tinytext NOT NULL,
                                            `Status` tinytext NOT NULL,
                                            `StartDate` tinytext ,
                                            `DueDate` tinytext,
                                            `AdminUsers` tinytext NOT NULL,
                                            `Participants` tinytext NOT NULL,
                                            `ParticipantProfiles` tinytext,
                                            `AdminUserProfiles` tinytext ,
                                            `DynamicLink` tinytext NOT NULL,
                                            `CreatedAt` tinytext,
                                            `UpdatedAt` tinytext
                                            )
                                            """
        val createTaskMessageTable =  """
                                            CREATE TABLE IF NOT EXISTS `TaskMessage` (
                                            `MessageID` int NOT NULL,
                                            `_id` tinytext,
                                            `TaskID` tinytext NOT NULL,
                                            `FromUserID` tinytext NOT NULL,
                                            `Content` tinytext NOT NULL,
                                            `ContentType` tinytext NOT NULL,
                                            `IsUploaded` tinyint DEFAULT 0,
                                            `IsDownloaded` tinyint DEFAULT 0,
                                            `IsDeleted` tinyint DEFAULT 0,
                                            `IsSent` tinyint DEFAULT 0,
                                            `IsReceived` tinyint DEFAULT 0,
                                            `IsRead` tinyint DEFAULT 0,
                                            `SentTime` tinytext,
                                            `DeliveredTime` tinytext,
                                            `ReadTime` tinytext,
                                             PRIMARY KEY (MessageID)
                                            )
                                            """
        val createTaskAttachmentTable =  """
                                            CREATE TABLE IF NOT EXISTS `TaskAttachment` (
                                            `_id` tinytext,
                                            `TaskAttachmentID` int NOT NULL,
                                            `TaskID` tinytext NOT NULL,
                                            `UserID` tinytext NOT NULL,
                                            `FileURL` tinytext NOT NULL,
                                            `IsUploaded` tinytext NOT NULL,
                                            `CreatedAt` tinytext,
                                            `UpdatedAt` tinytext,
                                             PRIMARY KEY (TaskAttachmentID)
                                            )
                                            """
        val createSaleTable =  """
                                            CREATE TABLE IF NOT EXISTS `Sale` (
                                            `_id` tinytext,
                                            `UserID` tinytext,
                                            `BusinessID` tinytext NOT NULL,
                                            `IsDeleted` BOOLEAN,
                                            `CustomerName` tinytext,
                                            `CustomerMobile` tinytext,
                                            `VehicleNumber` tinytext,
                                            `ProductName` tinytext,
                                            `ProductID` tinytext,
                                            `PaymentMode` tinytext,
                                            `Quantity` int,
                                            `Price` float,
                                            `CostPrice` float,
                                            `Discount` float,
                                            `SGST` float,
                                            `CGST` float,
                                            `IGST` float,
                                            `CESS` float,
                                            `VAT` float,
                                            `Tax` float,
                                            `FinalPrice` float,
                                            `SaleDate` tinytext,
                                            `SaleTime` bigint,
                                            `__v` int,
                                            `CreatedAt` tinytext,
                                            `UpdatedAt` tinytext,
                                             PRIMARY KEY (_id)
                                            )
                                            """
        val createProductStockTable =  """
                                            CREATE TABLE IF NOT EXISTS `${TableNames.productStock}` (
                                            `_id` tinytext,
                                            `UserID` tinytext,
                                            `BusinessID` tinytext NOT NULL,
                                            `ProductID`tinytext,
                                            `ActionID` tinytext,
                                            `IncreaseQuantity` int,
                                            `DecreaseQuantity` int,
                                            `TotalQuantity` int,
                                            `Comment` tinytext,
                                            `__v` int,
                                            `CreatedAt` tinytext,
                                            `UpdatedAt` tinytext,
                                             PRIMARY KEY (_id)
                                            )
                                            """
    }

}