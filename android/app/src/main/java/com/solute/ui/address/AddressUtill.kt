package com.solute.ui.address

import com.friendly.framework.feature.address.model.Address

class AddressUtill {
    fun formatToDisplay(address: Address?):String{
        var result = ""
        if(!address?.Name.isNullOrEmpty()){
            result += address?.Name
        }
        if(!address?.House.isNullOrEmpty()){
            result += " " + address?.House
        }
        if(!address?.FlatNumber.isNullOrEmpty()){
            result += " " + address?.FlatNumber
        }
        if(!address?.Floor.isNullOrEmpty()){
            result += " " + address?.Floor
        }
        if(!address?.Area.isNullOrEmpty()){
            result += " " + address?.Area
        }
        if(!address?.City.isNullOrEmpty()){
            result += " " + address?.City
        }
        if(!address?.State.isNullOrEmpty()){
            result += " " + address?.State
        }
        return  result
    }
}