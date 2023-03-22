//
//  ProductListVC.swift
//  BharatBandhu
//
//  Created by Vivek Singh on 18/03/23.
//

import UtilityKit

class ProductListVC: UtilityViewController {

    @IBOutlet weak var cartBtn: UIButton!
    
    @IBOutlet weak var tableView: UITableView!
    
    @IBOutlet weak var searchField: UITextField!
    
    
    @IBAction func onClickCart(_ sender: UIButton) {
        self.performSegue(withIdentifier: "GoToCart", sender: self)
    }
    
    var allProducts : [Product] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.tableView.delegate = self
        self.tableView.dataSource = self
        tableView.register(ProductCell.nib, forCellReuseIdentifier: ProductCell.identifier)
        self.startActivity()
        ProductViewModel.shared.onChange={prd in
            DispatchQueue.main.async {
                self.stopActivity()
                self.allProducts = prd
                self.reloadData()
            }
        }
        MediaFilesViewModel.shared.onChange={
            DispatchQueue.main.async {
                self.reloadData()
            }
        }
        loadProduct()
        searchField.addTarget(self, action: #selector(textFieldDidChange(_:)), for: .editingChanged)
    }
    
    @objc func textFieldDidChange(_ textField: UITextField) {
        ProductViewModel.shared.search(query: textField.text)
    }
    
    func reloadData(){
        self.tableView.reloadData()
    }
    
    func loadProduct() {
        if(SocketService.shared.connectionStatus == 1){
            if(ProductViewModel.shared.products.isEmpty){
                ProductViewModel.shared.loadProducts()
                MediaFilesViewModel.shared.fetchData()
            }
        }else{
            DispatchQueue.main.asyncAfter(deadline: .now() + 0.3){
                self.loadProduct()
            }
        }
    }
}
extension ProductListVC:UITableViewDataSource{
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        self.allProducts.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: ProductCell.identifier) as! ProductCell
        let prd = self.allProducts[indexPath.row]
        cell.sutupCell(prd: prd)
        return cell
    }
    
    
}
extension ProductListVC:UITableViewDelegate{
    
}
