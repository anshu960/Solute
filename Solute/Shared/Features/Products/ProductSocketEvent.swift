//
//  CusttomerEvent.swift
//  Solute
//
//  Created by Vivek Singh on 13/12/22.
//

import Foundation
enum ProductSocketEvent : String{
    case CREATE = "CREATE_PRODUCT"
    case UPDATE = "UPDATE_PRODUCT"
    case DELETE = "DELETE_PRODUCT"
    case RETRIEVE = "RETRIVE_PRODUCT"
    case UPDATE_IMAGE = "UPDATE_PRODUCT_IMAGE"
}
