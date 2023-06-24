//
//  NetworkManagers.swift
//  iosApp
//
//  Created by Daniyar Marklenovich on 24/6/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation


enum ApiError: Error {
    case errorOccured
    
    var description: String {
        switch self {
        case .errorOccured:
            return "Неожиданный ответ от сервера.\nПожалуйста, повторите попытку еще раз через некоторое время."
        }
    }
}

final class NetworkManager {
    // Signleton
    private init() { }
    static let shared = NetworkManager()
    
    ///  Константа URL-ссылки
    private let URL_PATH = "https://0d5c-212-112-100-3.ngrok-free.app/api/v1/users/register/"
    
    private var baseURL: URL {
        URL(string: URL_PATH)!
    }
    
    private let session = URLSession(configuration: .default)
    
    private func encode(_ params: [String:Any]) throws -> Data {
        try JSONSerialization.data(withJSONObject: params, options: .prettyPrinted)
    }
    
    public func registerUser(
        name: String,
        password: String,
        completion: @escaping (Bool, Error?) -> Void
    ) {
        // Создание запроса по URL
        var request = URLRequest(url: baseURL)
        request.httpMethod = "POST"
        
        request.addValue("application/json", forHTTPHeaderField: "Content-Type") // нужно чекнуть на сервере
        request.addValue("application/json", forHTTPHeaderField: "Accept") // и это тоже
        
        // Надо посмотреть эндпоинты на бэке, но т.к. он лежит, я не могу чекнуть это
        let parameters: [String:Any] = [
            "name": name,
            "password": password
        ]
        
        do {
            request.httpBody = try encode(parameters)
        } catch {
            completion(false, error)
            return
        }
        
        session.dataTask(with: request) { data, response, error in
            // Проверка на отсутствие ошибок в ответе сервера
            if let error = error {
                print("Post Request error occured: \(error.localizedDescription)")
                completion(false, error)
                return
            }
            
            // Проверка на статус-код ответ от сервера
            guard let httpResponse = response as? HTTPURLResponse,
                  (200...299).contains(httpResponse.statusCode)
            else {
                print("Invalid Response received from the server (not in range 200...299)")
                completion(false, ApiError.errorOccured)
                return
            }
            
            // Проверка, что данные вернулись
            guard let data else {
                print("nil Data received from the server")
                return
            }
            
            completion(true, nil)
            
        }
        .resume()
    }
    
}
