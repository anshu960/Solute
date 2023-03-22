//
//  WeekDaysTVCell.swift
//  JustFit
//
//  Created by Vivek Kumar on 22/05/19.
//  Copyright © 2019 Vivek Kumar. All rights reserved.
//

import UIKit

enum Day : String{
    case Monday = "Monday"
    case Tuesday = "Tuesday"
    case Wednesday = "Wednesday"
    case Thursday = "Thursday"
    case Friday = "Friday"
    case Saturday = "Saturday"
    case Sunday = "Sunday"
}

public class WeekDaysTVCell: BaseTVC {
    
    public static let nibName = "WeekDaysTVCell"
    public static let identifier = "WeekDaysTVCell"
    public static let nib = WeekDaysTVCell.getNib()
    class func getNib() -> UINib {
        return UINib(nibName: WeekDaysTVCell.nibName, bundle: Bundle(for: WeekDaysTVCell.self))
    }
    
    @IBOutlet weak var stackView: UIStackView!
    @IBOutlet weak var mondaybtn: UIButton!
    @IBOutlet weak var tuesdayBtn: UIButton!
    @IBOutlet weak var wednesdayBtn: UIButton!
    @IBOutlet weak var thursdayBtn: UIButton!
    @IBOutlet weak var fridayBtn: UIButton!
    @IBOutlet weak var saturdaybtn: UIButton!
    @IBOutlet weak var sundayBtn: UIButton!
    
    
    var days : [String] = []{
        didSet{
            updateValuesInUI()
        }
    }
    
    var onChnage : (([String])->Void)?
    
    var isEnabled : Bool{
        get{
           return self.isUserInteractionEnabled
        }
        set{
            self.isUserInteractionEnabled = newValue
        }
    }
    
    override public func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
        applyStyle()
    }
    
    override public func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
        
        // Configure the view for the selected state
    }
    
    func applyStyle(){
        var cornerRadius : CGFloat = self.mondaybtn.frame.height/2
        if(self.mondaybtn.frame.width != self.mondaybtn.frame.height){
            if(self.mondaybtn.frame.width < self.mondaybtn.frame.height){
                cornerRadius = self.mondaybtn.frame.width/2
            }else{
                cornerRadius = self.mondaybtn.frame.height/2
            }
        }        
        
        self.mondaybtn.layer.cornerRadius = cornerRadius
        self.tuesdayBtn.layer.cornerRadius = cornerRadius
        self.wednesdayBtn.layer.cornerRadius = cornerRadius
        self.thursdayBtn.layer.cornerRadius = cornerRadius
        self.fridayBtn.layer.cornerRadius = cornerRadius
        self.saturdaybtn.layer.cornerRadius = cornerRadius
        self.sundayBtn.layer.cornerRadius = cornerRadius
        
        
    }
    
    func updateValuesInUI() {
        if(self.days.contains(Day.Monday.rawValue)){
            self.mondaybtn.backgroundColor = UIColor.green
        }else{
            self.mondaybtn.backgroundColor = UIColor.gray
        }
        
        if(self.days.contains(Day.Tuesday.rawValue)){
            self.tuesdayBtn.backgroundColor = UIColor.green
        }else{
            self.tuesdayBtn.backgroundColor = UIColor.gray
        }
        
        if(self.days.contains(Day.Wednesday.rawValue)){
            self.wednesdayBtn.backgroundColor = UIColor.green
        }else{
            self.wednesdayBtn.backgroundColor = UIColor.gray
        }
        
        if(self.days.contains(Day.Thursday.rawValue)){
            self.thursdayBtn.backgroundColor = UIColor.green
        }else{
            self.thursdayBtn.backgroundColor = UIColor.gray
        }
        
        if(self.days.contains(Day.Friday.rawValue)){
            self.fridayBtn.backgroundColor = UIColor.green
        }else{
            self.fridayBtn.backgroundColor = UIColor.gray
        }
        
        if(self.days.contains(Day.Saturday.rawValue)){
            self.saturdaybtn.backgroundColor = UIColor.green
        }else{
            self.saturdaybtn.backgroundColor = UIColor.gray
        }
        
        if(self.days.contains(Day.Sunday.rawValue)){
            self.sundayBtn.backgroundColor = UIColor.green
        }else{
            self.sundayBtn.backgroundColor = UIColor.gray
        }
        if let chanegAction = self.onChnage{
            chanegAction(self.days)
        }
        
    }
    
    
    @IBAction func onclickMonday(_ sender: UIButton) {
        if let index = self.days.firstIndex(of: Day.Monday.rawValue){
            self.days.remove(at: index)
        }else{
            self.days.append(Day.Monday.rawValue)
        }
    }
    
    @IBAction func onclickTuesday(_ sender: UIButton) {
        if let index = self.days.firstIndex(of: Day.Tuesday.rawValue){
            self.days.remove(at: index)
        }else{
            self.days.append(Day.Tuesday.rawValue)
        }
    }
    
    @IBAction func onclickWednesday(_ sender: UIButton) {
        if let index = self.days.firstIndex(of: Day.Wednesday.rawValue){
            self.days.remove(at: index)
        }else{
            self.days.append(Day.Wednesday.rawValue)
        }
    }
    
    @IBAction func onclickThursday(_ sender: UIButton) {
        if let index = self.days.firstIndex(of: Day.Thursday.rawValue){
            self.days.remove(at: index)
        }else{
            self.days.append(Day.Thursday.rawValue)
        }
    }
    
    @IBAction func onclickFriday(_ sender: UIButton) {
        if let index = self.days.firstIndex(of: Day.Friday.rawValue){
            self.days.remove(at: index)
        }else{
            self.days.append(Day.Friday.rawValue)
        }
    }
    
    @IBAction func onclickDaturday(_ sender: UIButton) {
        if let index = self.days.firstIndex(of: Day.Saturday.rawValue){
            self.days.remove(at: index)
        }else{
            self.days.append(Day.Saturday.rawValue)
        }
    }
    
    @IBAction func onclickSunday(_ sender: UIButton) {
        if let index = self.days.firstIndex(of: Day.Sunday.rawValue){
            self.days.remove(at: index)
        }else{
            self.days.append(Day.Sunday.rawValue)
        }
    }
    
}
