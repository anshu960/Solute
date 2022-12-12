//
//  FileManager.swift
//  DocScanner
//
//  Created by Vivek Kumar on 5/5/18.
//  Copyright © 2018 Vivek. All rights reserved.
//

import UIKit
public class FileUtility{
    public static let shared = FileUtility()
    let fileManager = FileManager.default
    public let defaultPath : URL = {
        let path = try! FileManager.default.url(for:.documentDirectory, in: .userDomainMask, appropriateFor: nil, create: false)
        if FileManager.default.fileExists(atPath: path.path){
            return path
        }else{
            do {
                try FileManager.default.createDirectory(atPath: path.path, withIntermediateDirectories: false, attributes: nil)
            } catch let error as NSError {
                print(error.localizedDescription);
            }
            return path
        }
    }()
    
    public func getFileDirectory()->String {
        let paths = try! FileManager.default.url(for: .documentDirectory, in: .userDomainMask,appropriateFor:nil,create: false).appendingPathComponent("AllFiles")
        
        if FileManager.default.fileExists(atPath: paths.path) == true{
            //            print("exist\n  \(paths.path)")
        }else{
            do {
                try FileManager.default.createDirectory(atPath: paths.path, withIntermediateDirectories: false, attributes: nil)
            } catch let error as NSError {
                print(error.localizedDescription);
            }
        }
        return paths.path
    }
    
    public func createFolder(name:String) -> String {
        
        let currentPath = self.defaultPath.appendingPathComponent(name)
        if(fileManager.fileExists(atPath: currentPath.path)){
            return "Folder already exist"
        }else{
            do{
                try fileManager.createDirectory(atPath: currentPath.path, withIntermediateDirectories: true, attributes: nil)
                return "Folder created"
            }catch{
                return "unable create folder due to \n \(error.localizedDescription.description)"
            }
        }
    }
    
    public func createFolder(directory:URL,name:String) -> String {
        let currentPath = directory.appendingPathComponent(name)
        if(fileManager.fileExists(atPath: currentPath.path)){
            return "Folder already exist with same name"
        }else{
            do{
                try fileManager.createDirectory(atPath: currentPath.path, withIntermediateDirectories: true, attributes: nil)
                return ""
            }catch{
                return "unable create folder due to \n \(error.localizedDescription.description)"
            }
        }
    }
    
    public func renameFile(atPath path:URL,newName name : String) -> String {
        let currentPath = path
        do {
            try  fileManager.moveItem(at: path, to: currentPath.deletingLastPathComponent().appendingPathComponent("\(name).pdf"))
            return ""
        } catch  {
            return error.localizedDescription.description
        }
    }
    
    public func moveFile(from fromUrl: URL, to toUrl:URL) -> String {
        do {
            try FileManager.default.moveItem(at: fromUrl, to: toUrl)
            return ""
        } catch {
            return error.localizedDescription
        }
    }
    
    public func renameDirectory(atPath path : URL, newName name : String) -> String {
        do {
            try  fileManager.moveItem(at: path, to: path.deletingLastPathComponent().appendingPathComponent("\(name)"))
            
            return ""
        } catch  {
            return error.localizedDescription
        }
    }
    
    public func scanDirectory()->[URL]{
        do {
            var fileURLs = try fileManager.contentsOfDirectory(at: self.defaultPath, includingPropertiesForKeys: nil)
            if let index = fileURLs.firstIndex(of: defaultPath.appendingPathComponent(".DS_Store")){
                fileURLs.remove(at: index)
            }
            return fileURLs
        } catch {
            return []
        }
    }
    
    public func scanDirectory(directory :URL)->[File]{
        var allFiles : [File] = []
        var allFileUrl : [URL] = []
        do {
            allFileUrl = try fileManager.contentsOfDirectory(at: directory, includingPropertiesForKeys: nil)
            if let index = allFileUrl.firstIndex(of: defaultPath.appendingPathComponent(".DS_Store")){
                allFileUrl.remove(at: index)
            }
        } catch {
            print(error.localizedDescription)
        }
        for fileUrl in allFileUrl{
            let file = File.init(fileUrl: fileUrl)
            if(file.type != .undefined && file.name != ".DS_Store"){
                allFiles.append(File.init(fileUrl: fileUrl))
            }
        }
        return allFiles
    }
    
    public func writeFile(file:Data,fileName:String){
        let path = defaultPath.appendingPathComponent(fileName)
        fileManager.createFile(atPath: path.path, contents: file, attributes: nil)
    }
    
    public func writeFile(directory:URL,file:Data,fileName:String) -> String {
        let path = directory.appendingPathComponent(fileName)
        fileManager.createFile(atPath: path.path, contents: file, attributes: nil)
        return ""
    }
    public func writeFile(directory:URL,file:Data) {
        fileManager.createFile(atPath: directory.path, contents: file, attributes: nil)
    }
    
    
    public func deleteFile(url:URL) -> String {
        do{
            try fileManager.removeItem(at: url)
            return ""
        }catch{
            return(error.localizedDescription.description)
        }
    }
    
    public func readFile(atPath path : URL) -> Data? {
        let fileData = fileManager.contents(atPath: path.path)
        return fileData
    }
    
    public func getCachedDirectory() -> URL? {
        let currentPath = try! FileManager.default.url(for:.documentDirectory, in: .userDomainMask, appropriateFor: nil, create: false).appendingPathComponent("cachedDirectory")
        
        if(self.fileManager.fileExists(atPath: currentPath.path)){
            return currentPath
        }else{
            do{
                try fileManager.createDirectory(atPath: currentPath.path, withIntermediateDirectories: true, attributes: nil)
                return currentPath
            }
            catch{
                return nil
            }
        }
    }
    
    public func getSize(ofDirectory directoryUrl : URL) -> String {
        // get your directory url
        var size = "Bytes"
        // check if the url is a directory
        if (try? directoryUrl.resourceValues(forKeys: [.isDirectoryKey]))?.isDirectory == true {
            // lets get the folder files
            var folderSize = 0
            (try? FileManager.default.contentsOfDirectory(at: directoryUrl, includingPropertiesForKeys: nil))?.lazy.forEach {
                folderSize += (try? $0.resourceValues(forKeys: [.totalFileAllocatedSizeKey]))?.totalFileAllocatedSize ?? 0
            }
            // format it using NSByteCountFormatter to display it properly
            let  byteCountFormatter =  ByteCountFormatter()
            byteCountFormatter.allowedUnits = .useBytes
            byteCountFormatter.countStyle = .file
            size = (folderSize/1000000).description + "MB"
        }
        return size
    }
    
    public func getFileSize(ofFolder url:URL)->String  {
        // get your directory url
        var size = ""
        // get your directory url
        let documentsDirectoryURL =  FileManager.default.urls(for: .documentDirectory, in: .userDomainMask).first!
        // check if the url is a directory
        if (try? documentsDirectoryURL.resourceValues(forKeys: [.isDirectoryKey]))?.isDirectory == true {
            var folderSize = 0
            (FileManager.default.enumerator(at: documentsDirectoryURL, includingPropertiesForKeys: nil)?.allObjects as? [URL])?.lazy.forEach {
                folderSize += (try? $0.resourceValues(forKeys: [.totalFileAllocatedSizeKey]))?.totalFileAllocatedSize ?? 0
            }
            let  byteCountFormatter =  ByteCountFormatter()
            byteCountFormatter.allowedUnits = .useBytes
            byteCountFormatter.countStyle = .file
            size = (folderSize/1000000).description + "MB"
            
        }
        return size
    }
    public func fileSize(fileAt fileUrl:URL) -> String {
        var fileSize : Double = 0
        do {
            //return [FileAttributeKey : Any]
            let attr = try FileManager.default.attributesOfItem(atPath: fileUrl.path)
            fileSize = attr[FileAttributeKey.size] as! Double
            //if you convert to NSDictionary, you can get file size old way as well.
            let dict = attr as NSDictionary
            fileSize = Double(dict.fileSize())
        } catch {
            print("Error: \(error)")
        }
        
        var formattedFileSize = ""
        if(fileSize >= 1000){
            fileSize = fileSize/1000
            if(fileSize >= 1000){
                fileSize = fileSize/1000
                formattedFileSize = fileSize.rounded(places: 1).description + "MB"
            }else{
                formattedFileSize = fileSize.rounded(places: 1).description + "KB"
            }
        }else{
            formattedFileSize = fileSize.rounded(places: 1).description + "Bytes"
        }
        return formattedFileSize
    }
}


public enum FileType {
    case folder,pdf,doc,txt,png,jpg,image,sqlite,video,undefined
}
public enum FileExtension : String{
    case PDF = ".pdf"
    case JPEG = ".jpg"
    case PNG = ".png"
    case Folder = ""
    case Text = ".txt"
    case HTML = ".html"
}
public struct File {
    let name : String!
    let type : FileType!
    let size : String!
    let path : String!
    let url : URL!
    init(fileUrl:URL) {
        path = fileUrl.path
        self.url = fileUrl
        let fileExtension = fileUrl.pathExtension
        switch fileExtension {
        case "":
            name = fileUrl.lastPathComponent
            type = .folder
            size = FileUtility.shared.getSize(ofDirectory: self.url)
        case "pdf":
            name = fileUrl.deletingPathExtension().lastPathComponent
            type = .pdf
            self.size = FileUtility.shared.fileSize(fileAt: self.url)
        case "jpg":
            name = fileUrl.deletingPathExtension().lastPathComponent
            type = .jpg
            self.size = FileUtility.shared.fileSize(fileAt: self.url)
        case "png":
            name = fileUrl.deletingPathExtension().lastPathComponent
            type = .png
            self.size = FileUtility.shared.fileSize(fileAt: self.url)
        case "sqlite":
            name = fileUrl.deletingPathExtension().lastPathComponent
            type = .sqlite
            self.size = FileUtility.shared.fileSize(fileAt: self.url)
        case "mp4":
            name = fileUrl.deletingPathExtension().lastPathComponent
            type = .video
            self.size = FileUtility.shared.fileSize(fileAt: self.url)
        default:
            name = fileUrl.lastPathComponent
            type = .undefined
            size = FileUtility.shared.getFileSize(ofFolder: self.url)
        }
    }
}

