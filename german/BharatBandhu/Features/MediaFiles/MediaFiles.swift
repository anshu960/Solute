// This file was generated from JSON Schema using quicktype, do not modify it directly.
// To parse the JSON, add this file to your project and do:
//
//   let product = try? newJSONDecoder().decode(Product.self, from: jsonData)

import Foundation
import Foundation

// MARK: - MediaFiles
struct MediaFiles: Codable {
    var id: String?
    var uniqueID: Int?
    var userID, businessID, featureObjectID: String?
    var isDeleted: Bool?
    var fileURL: String?
    var createdAt, updatedAt: String?
    var v: Int?

    enum CodingKeys: String, CodingKey {
        case id = "_id"
        case uniqueID = "UniqueID"
        case userID = "UserID"
        case businessID = "BusinessID"
        case featureObjectID = "FeatureObjectID"
        case isDeleted = "IsDeleted"
        case fileURL = "FileURL"
        case createdAt = "CreatedAt"
        case updatedAt = "UpdatedAt"
        case v = "__v"
    }
}

@propertyWrapper public struct NilOnFail<T: Codable>: Codable {
    
    public let wrappedValue: T?
    public init(from decoder: Decoder) throws {
        wrappedValue = try? T(from: decoder)
    }
    public init(_ wrappedValue: T?) {
        self.wrappedValue = wrappedValue
    }
}
