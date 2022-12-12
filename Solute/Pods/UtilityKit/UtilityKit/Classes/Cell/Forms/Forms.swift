//
//  Forms.swift
//  UtilityKit
//
//  Created by Vivek Kumar on 29/08/19.
//

import UIKit

public enum FieldType{
    case TextField,TextArea,AddNew,ImageCollection,Valuelabel,WeekDays,DatePicker,Switch,Segment,Option
}

struct FormValidatoin {
    
}

public struct Field {
    let id : String
    let type : FieldType
    let height : CGFloat = 80
    var value : Any?
    var options : [Any] = []
    var dependent : String? = nil
    var valueKey : String?
    let regex : String?
    let placeholder : String
    let title : String
    let errorMessage : String?
    var error : FormError?
    init(id: String, type: FieldType, value: Any, options: [Any], regex: String?, placeholder: String, title: String) {
        self.id = id
        self.type = type
        self.value = value
        self.options = options
        self.regex = regex
        self.placeholder = placeholder
        self.title = title
        self.errorMessage = nil
        self.error = nil
    }
    init(id: String, type: FieldType, value: Any, options: [Any], regex: String?, placeholder: String, title: String,valueKey:String) {
        self.id = id
        self.type = type
        self.value = value
        self.options = options
        self.regex = regex
        self.placeholder = placeholder
        self.title = title
        self.errorMessage = nil
        self.error = nil
        self.valueKey = valueKey
    }
    
}

public struct FormError{
    let id : String
    var message : String
}

public class Forms: UIView {
    
    public var controller : UIViewController?
    
    let footerView = UIView(frame: CGRect(x: 0, y: 0, width: 300, height: 50))
    let submitButton = UIButton(type: .roundedRect)
    var onSubmit : (([String:Any],[String:Any])->Void)?
    public var fields : [Field] = []{
        didSet{
            self.onChange?(fields)
        }
    }
    let tableView = UITableView()
    var values : [String:Any] = [:]
    var errors : [String:Any] = [:]
    var onChange : (([Field])->Void)?
    /*
     // Only override draw() if you perform custom drawing.
     // An empty implementation adversely affects performance during animation.
     override func draw(_ rect: CGRect) {
     // Drawing code
     }
     */
   public func refresh(){
        self.tableView.reloadData()
    }
    
    
    public override func awakeFromNib() {
        super.awakeFromNib()
        self.tableView.frame = self.bounds
        self.addSubview(self.tableView)
        self.tableView.delegate = self
        self.tableView.dataSource = self
        self.tableView.backgroundColor = UIColor.clear
        self.tableView.separatorStyle = .none
        registerCells()
        self.tableView.layer.cornerRadius = self.layer.cornerRadius
    }
    
    func registerCells(){
        self.tableView.register(TextInputTVC.nib, forCellReuseIdentifier: TextInputTVC.identifier)
        self.tableView.register(TextAreaTVC.nib, forCellReuseIdentifier: TextAreaTVC.identifier)
        self.tableView.register(DatePickerTVC.nib, forCellReuseIdentifier: DatePickerTVC.identifier)
        self.tableView.register(SwitchTVC.nib, forCellReuseIdentifier: SwitchTVC.identifier)
        self.tableView.register(SegmentTVC.nib, forCellReuseIdentifier: SegmentTVC.identifier)
        self.tableView.register(OptionTVC.nib, forCellReuseIdentifier: OptionTVC.identifier)
    }
    
   public func updateOptions(id:String,optios:[Any]){
        var count = 0
        for field in self.fields{
            if(field.id == id){
                var updatedField = field
                updatedField.options = optios
                self.fields[count] = updatedField
            }
            count+=1
        }
        self.refresh()
    }
    
    public func validate()->[String:Any]{
        return self.errors
    }
    public func getData()->[String:Any]{
        var values : [String:Any] = [:]
        for field in self.fields{
            values[field.id] = field.value
        }
        return values
    }
    
    public  func addTextField(id:String,title:String,placeholder:String,value:String){
        self.fields.append(Field(id: id, type: .TextField, value: value, options: [], regex: nil, placeholder: placeholder, title: title))
    }
    
    
    public func addSwitchField(id:String,title:String,value:Bool){
        self.fields.append(Field(id: id, type: .Switch, value: value, options: [], regex: nil, placeholder: "", title: title))
    }
    
    public func addSegmentField(id:String,title:String,value:String,options:[String]){
        self.fields.append(Field(id: id, type: .Segment, value: value, options: options, regex: nil, placeholder: "", title: title))
    }
    
    public func addDateField(id:String,title:String,value:Any){
        self.fields.append(Field(id: id, type: .DatePicker, value: value, options: [], regex: nil, placeholder: "", title: title))
    }
    
    public func addOptionField(id:String,title:String,placeholder:String,value:Any,options:[Any],valueKey:String){
        self.fields.append(Field(id: id, type: .Option, value: value, options: [], regex: nil, placeholder: placeholder, title: title,valueKey:valueKey))
    }
    
    
    public func addSubmit(title:String,backgroundColor:UIColor,textColor:UIColor,onSubmit:@escaping ([String:Any],[String:Any])->Void){
        self.footerView.backgroundColor = UIColor.clear
        submitButton.backgroundColor = backgroundColor
        submitButton.tintColor = textColor
        submitButton.setTitleColor(textColor, for: .normal)
        self.onSubmit = onSubmit
        self.submitButton.setTitle(title, for: .normal)
        self.submitButton.layer.cornerRadius = 16
        self.submitButton.addTarget(self, action: #selector(onClickSubmit(_:)), for: .touchUpInside)
    }
    
    @objc func onClickSubmit(_ sender:UIButton) {
        if let submitAction = self.onSubmit{
            submitAction(self.errors,self.getData())
        }
    }
    
    public override func didMoveToWindow() {
        self.tableView.frame = self.bounds
        self.tableView.reloadData()
//        self.tableView.translatesAutoresizingMaskIntoConstraints = false
        
        if(self.onSubmit != nil){
            self.submitButton.frame = CGRect(x: 0, y: 0, width: 150, height: 36)
            //            self.submitButton.center = self.footerView.center
            self.addSubview(self.submitButton)
            
            NSLayoutConstraint.activate([
                self.tableView.topAnchor.constraint(equalTo: self.topAnchor),
                self.tableView.leftAnchor.constraint(equalTo: self.leftAnchor),
                self.tableView.rightAnchor.constraint(equalTo: self.rightAnchor),
                self.tableView.bottomAnchor.constraint(equalTo: self.bottomAnchor, constant: 50)
                ])
            
            
            self.footerView.frame = CGRect(x: 0, y: self.tableView.bounds.height-50, width: self.bounds.width, height: 50)
            self.addSubview(self.footerView)
            self.footerView.clipsToBounds = true
            NSLayoutConstraint.activate([
                self.footerView.leftAnchor.constraint(equalTo: self.leftAnchor),
                self.footerView.rightAnchor.constraint(equalTo: self.rightAnchor),
                self.footerView.bottomAnchor.constraint(equalTo: self.bottomAnchor),
                self.footerView.topAnchor.constraint(equalTo: self.tableView.bottomAnchor),
                ])
            
            self.submitButton.center = self.footerView.center
            self.bringSubviewToFront(self.submitButton)
            
        }else{
            NSLayoutConstraint.activate([
                self.tableView.topAnchor.constraint(equalTo: self.topAnchor),
                self.tableView.leftAnchor.constraint(equalTo: self.leftAnchor),
                self.tableView.rightAnchor.constraint(equalTo: self.rightAnchor),
                self.tableView.bottomAnchor.constraint(equalTo: self.bottomAnchor)
                ])
        }
    }
    
    
    
}
extension Forms:UITableViewDelegate,UITableViewDataSource{
    public func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    public func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.fields.count
    }
    public func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let field = self.fields[indexPath.row]
        var cell : BaseTVC!
        switch field.type {
        case .TextField:
            cell = tableView.dequeueReusableCell(withIdentifier: TextInputTVC.identifier) as! TextInputTVC
        case .TextArea:
            cell = tableView.dequeueReusableCell(withIdentifier: TextInputTVC.identifier) as! TextInputTVC
        case .DatePicker:
            cell = tableView.dequeueReusableCell(withIdentifier: DatePickerTVC.identifier) as! DatePickerTVC
        case .Switch:
            cell = tableView.dequeueReusableCell(withIdentifier: SwitchTVC.identifier) as! SwitchTVC
        case .Segment:
            cell = tableView.dequeueReusableCell(withIdentifier: SegmentTVC.identifier) as! SegmentTVC
        case .Option:
            cell = tableView.dequeueReusableCell(withIdentifier: OptionTVC.identifier) as! OptionTVC
            cell.controller = self.controller
        default:
            //            cell = UITableViewCell(style: .default, reuseIdentifier: "cell")
            break
        }
        cell.field = field
        cell.onChange = {field in
            self.fields[indexPath.row] = field
        }
        return cell
    }
    public func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let field = self.fields[indexPath.row]
        switch field.type {
        case .Option:
            let vc = OptionsVC.instantiate()
            vc.field = field
            vc.onSelect = {updatedField in
                self.fields[indexPath.row] = updatedField
                self.refresh()
            }
            vc.present(onVc: self.controller)
        default:
            break
        }
    }
}
