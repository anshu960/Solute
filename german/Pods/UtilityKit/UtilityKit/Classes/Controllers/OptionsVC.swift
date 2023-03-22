//
//  OptionsVC.swift
//  UtilityKit
//
//  Created by Vivek Kumar on 13/09/19.
//

import UIKit

class OptionsVC: UIViewController {
    
    
    public static let nibName = "OptionsVC"
    public static let identifier = "OptionsVC"
    public static let nib = OptionsVC.getNib()
    public static let cellHeight : CGFloat = 150
    class func getNib() -> UINib {
        return UINib(nibName: OptionsVC.nibName, bundle: Bundle(for: OptionsVC.self))
    }
    
    class func instantiate()->OptionsVC{
       return OptionsVC(nibName: "OptionsVC", bundle: Bundle(for: OptionsVC.self))
//        return nib.instantiate(withOwner: OptionsVC.self, options: nil).first as! OptionsVC
    }
    
    func present(onVc:UIViewController?) {
        self.title = self.field?.title
        if let navVc = onVc?.navigationController{
            navVc.pushViewController(self, animated: true)
        }else{
            onVc?.present(self, animated: true, completion: nil)
        }
    }
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.tableView.delegate = self
        self.tableView.dataSource = self
    }
    
    var tableView: UITableView = UITableView()
    
    @IBOutlet weak var titleBar: UINavigationBar!
    
    @IBOutlet weak var doneBtn: UIBarButtonItem!
    
    @IBAction func onClickDone(_ sender: UIBarButtonItem) {
        
        
    }
    var valueKey : String?
    var field : Field?{
        didSet{
            self.options = self.field!.options
        }
    }
    var options : [Any] = []
    var onSelect : ((Field)->Void)?
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.tableView.reloadData()
        if self.navigationController != nil{
            self.titleBar.removeFromSuperview()
        }
        self.tableView = UITableView()
        self.tableView.removeFromSuperview()
        self.tableView.frame = self.view.bounds
        self.view.addSubview(self.tableView)
        self.tableView.delegate = self
        self.tableView.dataSource = self
        self.tableView.reloadData()
    }
    
    /*
     // MARK: - Navigation
     
     // In a storyboard-based application, you will often want to do a little preparation before navigation
     override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
     // Get the new view controller using segue.destination.
     // Pass the selected object to the new view controller.
     }
     */
    
}

extension OptionsVC:UITableViewDelegate,UITableViewDataSource{
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return options.count
    }
    
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = UITableViewCell(style: .default, reuseIdentifier: "cell")
        let option = self.field!.options[indexPath.row]
        if let key = self.field?.valueKey,let data = option as? [String:Any],let val = data[key] as? String{
            cell.textLabel?.text = val
        }else{
            cell.textLabel?.text = option as? String
        }
        return cell
    }
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        self.field?.value = options[indexPath.row]
        self.onSelect?(self.field!)
        self.navigationController?.popViewController(animated: true)
    }
}
