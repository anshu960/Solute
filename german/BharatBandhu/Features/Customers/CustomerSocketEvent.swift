//
//  CusttomerEvent.swift
//  Solute
//
//  Created by Vivek Singh on 13/12/22.
//

import Foundation
enum CustomerSocketEvent : String{
    case CREATE_CUSTOMER = "CREATE_CUSTOMER"
    case UPDATE_CUSTOMER = "UPDATE_CUSTOMER"
    case RETRIVE_CUSTOMER = "RETRIVE_CUSTOMER"
    case FIND_CUSTOMER_BY_MOBILE = "FIND_CUSTOMER_BY_MOBILE"
    case DELETE_CUSTOMER = "DELETE_CUSTOMER"
}
