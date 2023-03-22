// This file was generated from JSON Schema using quicktype, do not modify it directly.
// To parse the JSON, add this file to your project and do:
//
//   let product = try? newJSONDecoder().decode(Product.self, from: jsonData)

import Foundation

// MARK: - Product
struct Product: Codable {
    var id: String?
    var uniqueID, productID, sku: Int?
    var businessID, userID, status: String?
    var isDeleted: Bool?
    var name, description: String?
    var manageInventory, taxIncluded: Bool?
    var sgst, cgst, igst, cess: Int?
    var vat, discount: Int?
    var mrp, price, costPrice, finalPrice: Double?
    var tax: Int?
    var createdAt, updatedAt: String?
    var v: Int?

    enum CodingKeys: String, CodingKey {
        case id = "_id"
        case uniqueID = "UniqueID"
        case productID = "ProductID"
        case sku = "SKU"
        case businessID = "BusinessID"
        case userID = "UserID"
        case status = "Status"
        case isDeleted = "IsDeleted"
        case name = "Name"
        case description = "Description"
        case manageInventory = "ManageInventory"
        case taxIncluded = "TaxIncluded"
        case sgst = "SGST"
        case cgst = "CGST"
        case igst = "IGST"
        case cess = "CESS"
        case vat = "VAT"
        case discount = "Discount"
        case mrp = "MRP"
        case price = "Price"
        case costPrice = "CostPrice"
        case finalPrice = "FinalPrice"
        case tax = "Tax"
        case createdAt = "CreatedAt"
        case updatedAt = "UpdatedAt"
        case v = "__v"
    }
}
