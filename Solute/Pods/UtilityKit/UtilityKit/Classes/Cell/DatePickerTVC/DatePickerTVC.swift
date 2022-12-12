//
//  DatePickerTVC.swift
//  UtilityKit
//
//  Created by Vivek Kumar on 08/09/19.
//

import UIKit

public class DatePickerTVC: BaseTVC {
    
    public static let nibName = "DatePickerTVC"
    public static let identifier = "DatePickerTVC"
    public static let nib = DatePickerTVC.getNib()
    class func getNib() -> UINib {
        return UINib(nibName: DatePickerTVC.nibName, bundle: Bundle(for: DatePickerTVC.self))
    }
    
    @IBOutlet weak var containerView: UIView!
    
    @IBOutlet weak var titlelbl: UILabel!
    
    @IBOutlet weak var dateBtn: UIButton!
    
    @IBAction func onClickDate(_ sender: Any) {
        UIApplication.shared.keyWindow?.resignFirstResponder()
        self.presentDatePicker()
    }
    
    var date : Date = Date()
    var datePickerContainer = UIView()
    var tapGesture : UITapGestureRecognizer!
    var datePicker = UIDatePicker(frame: CGRect(x: 0, y: 0, width: 300, height: 300))
    var datePickerToolBar = UIToolbar(frame: CGRect(x: 0, y: 0, width: 300, height: 50))
    var formField : Field!{
        didSet{
            self.titlelbl.text = self.field.title
            if let timestamp = self.field.value as? String,timestamp != ""{
                self.dateBtn.setTitle(Date.formatedDate(timestamp: timestamp), for: .normal)
                self.dateBtn.tintColor = UIColor.blue
            }else{
                self.dateBtn.tintColor = UIColor.lightGray
                self.dateBtn.setTitle(Date.getCurrentDate(), for: .normal)
            }
        }
    }
    
    func presentDatePicker(){
        if let window = UIApplication.shared.keyWindow,!self.datePickerContainer.isDescendant(of: window){
            self.datePickerContainer.frame = window.bounds
            window.addSubview(self.datePickerContainer)
            
            self.tapGesture = UITapGestureRecognizer(target: self, action: #selector(onTap(_:)))
            self.datePickerContainer.addGestureRecognizer(self.tapGesture)
            
            self.datePickerToolBar.frame = CGRect(x: 0, y: self.datePickerContainer.bounds.height, width: self.datePickerContainer.bounds.width, height: 50)
            
            
            self.datePicker.frame = CGRect(x: 0, y: self.datePickerContainer.bounds.height, width: self.datePickerContainer.bounds.width, height: 0)
            
            self.datePicker.date = self.date
            self.datePicker.datePickerMode = .date
            self.datePicker.addTarget(self, action: #selector(onChangeDate(_:)), for: .allEvents)
            self.datePicker.backgroundColor = UIColor.white
            
            
            self.datePickerContainer.addSubview(self.datePicker)
            self.datePickerContainer.addSubview(datePickerToolBar)
            
            self.datePickerToolBar.setItems(nil, animated: false)
            UIView.animate(withDuration: 0.4,animations:  {
                self.datePicker.frame = CGRect(x: 0, y: window.bounds.maxY-300, width: window.frame.width, height: 300)
                self.datePickerToolBar.frame = CGRect(x: 0, y: window.bounds.maxY-350, width: window.frame.width, height: 50)
            }){ (idCompleted) in
                let flexibleBarbuttonItem = UIBarButtonItem(barButtonSystemItem: .flexibleSpace, target: nil, action: nil)
                let fixedBarbuttonItem = UIBarButtonItem(barButtonSystemItem: .fixedSpace, target: nil, action: nil)
                fixedBarbuttonItem.width = 10
                let doneItem = UIBarButtonItem(barButtonSystemItem: .done, target: self, action: #selector(self.oncClickDone(_:)))
                self.datePickerToolBar.setItems([flexibleBarbuttonItem,doneItem,fixedBarbuttonItem], animated: true)
            }
        }else{
            self.remveDatePicker()
        }
    }
    
    @objc func oncClickDone(_ sender:UIBarButtonItem) {
        self.remveDatePicker()
    }
    
    func remveDatePicker() {
        self.datePickerContainer.removeGestureRecognizer(self.tapGesture)
        UIView.animate(withDuration: 0.3, animations: {
            self.datePicker.frame = CGRect(x: 0, y: self.datePickerContainer.bounds.maxY, width: self.datePicker.bounds.width, height: self.datePicker.bounds.height)
            self.datePickerToolBar.frame = CGRect(x: 0, y: self.datePickerContainer.bounds.maxY, width: self.datePicker.bounds.width, height: 50)

        }) { (idCompleted) in
            self.datePickerContainer.removeFromSuperview()
            self.datePicker.removeFromSuperview()
            self.datePickerToolBar.removeFromSuperview()
        }
    }
    
    @objc func onChangeDate(_ datePicker : UIDatePicker) {
        self.date = datePicker.date
        self.field.value = date.linuxTimestamp()
        self.onChange?(self.field)
//        self.remveDatePicker()
    }
    
    @objc func onTap(_ gesture:UITapGestureRecognizer) {
        self.remveDatePicker()
    }
    
    
    public override var field: Field!{
        get{
            return self.formField
        }
        set{
            self.formField = newValue
        }
    }
    
    override public func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }
    
    override public func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
        // Configure the view for the selected state
    }
}

