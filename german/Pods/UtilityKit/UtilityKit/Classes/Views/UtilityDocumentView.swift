//
//  UtilityFileView.swift
//  Pods-UtilityKit_Example
//
//  Created by Vivek Kumar on 18/09/18.
//

import UIKit
import WebKit
@IBDesignable
class UtilityDocumentView: UIView {

    var contentView:UIView?
    let nibName = "UtilityDocumentView"

    @IBOutlet var webView: WKWebView!

    @IBOutlet var activityIndicator: UIActivityIndicatorView!

   @IBInspectable public var contentViewColor : UIColor {
        get{
            return self.webView.scrollView.backgroundColor ?? UIColor.clear
        }
        set{
            self.webView.scrollView.backgroundColor = newValue
        }
    }

    public var document:File? = nil{
        didSet{
            if let file = self.document{
                self.load(with: file)
            }
        }
    }

    public var url : URL? = nil{
        didSet{
            if let fileUrl = self.url{
                self.load(with: fileUrl)
            }
        }
    }

    public func loadVideo(with url:URL) {
        self.activityIndicator.stopAnimating()
        self.load(with: url)
    }
    
    public func load(with url:URL){
        self.activityIndicator.startAnimating()
        let request = URLRequest(url: url)
        self.webView.load(request)
    }

    public func load(with file:File){
        self.load(with: file.url!)
        switch file.type! {
        case .video:
            self.loadVideo(with: file.url!)
        default:
            self.load(with: file.url!)
            self.activityIndicator.startAnimating()
        }
    }

    override init(frame: CGRect) {
        super.init(frame: frame)
//        self.xibSetup()
    }
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
//        self.xibSetup()
    }

    func xibSetup() {
        guard let view = loadViewFromNib() else { return }
        view.frame = bounds
        view.autoresizingMask =
            [.flexibleWidth, .flexibleHeight]
        addSubview(view)
        self.contentView = view
    }

    func loadViewFromNib() -> UIView? {
        let bundle = Bundle(for: type(of: self))
        let nib = UINib(nibName: self.nibName, bundle: bundle)
        return nib.instantiate(
            withOwner: self,
            options: nil).first as? UIView
    }

    override func prepareForInterfaceBuilder() {
        super.prepareForInterfaceBuilder()
        self.prepareForInterfaceBuilder()
    }

    override func awakeFromNib() {
        super.awakeFromNib()
        self.webView.uiDelegate = self
        self.webView.navigationDelegate = self
        self.activityIndicator.startAnimating()
    }
}

//MARK:WebViewNavigation
extension UtilityDocumentView:WKUIDelegate,WKNavigationDelegate{
    func webView(_ webView: WKWebView, didFinish navigation: WKNavigation!) {
        self.activityIndicator.stopAnimating()
    }
}
