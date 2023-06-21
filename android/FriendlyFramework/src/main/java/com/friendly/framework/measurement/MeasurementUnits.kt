package com.friendly.framework.measurement

data class Unit(val name:String)
data class MeasurementUnit(val name:String,val units:ArrayList<Unit>)
class MeasurementUnits {
    var allUnits : ArrayList<MeasurementUnit> = arrayListOf()

    fun prepare(){
        allUnits = arrayListOf()

        val pieceUnit = Unit("Per Unit")
        val pieceMeasurementUnit = MeasurementUnit("Unit/Each", arrayListOf(pieceUnit))

        val lengthUnits : ArrayList<Unit> = arrayListOf()
        lengthUnits.add(Unit("Millimeter"))
        lengthUnits.add(Unit("Centimeter"))
        lengthUnits.add(Unit("Meter"))
        lengthUnits.add(Unit("Kilometer"))
        lengthUnits.add(Unit("Foot"))
        lengthUnits.add(Unit("Inch"))
        lengthUnits.add(Unit("Yard"))
        val lengthMeasurementUnit = MeasurementUnit("Length", lengthUnits)

        val massUnits : ArrayList<Unit> = arrayListOf()
        massUnits.add(Unit("Milligram"))
        massUnits.add(Unit("Gram"))
        massUnits.add(Unit("Kilogram"))
        massUnits.add(Unit("Ounce"))
        massUnits.add(Unit("Pound"))
        val massMeasurementUnit = MeasurementUnit("Weight", massUnits)

        val volumeUnits : ArrayList<Unit> = arrayListOf()
        volumeUnits.add(Unit("Milliliter"))
        volumeUnits.add(Unit("Centiliter"))
        volumeUnits.add(Unit("Liter"))
        volumeUnits.add(Unit("UsGallon"))
        val volumeMeasurementUnit = MeasurementUnit("Weight", volumeUnits)

        val areaUnits : ArrayList<Unit> = arrayListOf()
        areaUnits.add(Unit("SquareCentimeter"))
        areaUnits.add(Unit("SquareDecimeter"))
        areaUnits.add(Unit("SquareMeter"))
        areaUnits.add(Unit("SquareInch"))
        areaUnits.add(Unit("SquareFoot"))
        val areaMeasurementUnit = MeasurementUnit("Weight", areaUnits)


        allUnits.add(pieceMeasurementUnit)
        allUnits.add(lengthMeasurementUnit)
        allUnits.add(massMeasurementUnit)
        allUnits.add(volumeMeasurementUnit)
        allUnits.add(areaMeasurementUnit)

    }

}