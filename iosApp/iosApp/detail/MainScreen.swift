//
//  MainScreen.swift
//  iosApp
//
//  Created by Daniyar Marklenovich on 24/6/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct MainScreen: View {
    
    @State private var name = ""
    @State private var password = ""
    @State private var confirm = ""
    @State private var signedUp = false
    
    var body: some View {
        VStack {
            Spacer()
            
            // Если пользователь зарег-ся, показывать соответсвующее представление
            if signedUp {
                SuccessView()
            } else {
                // А если нет, показывать форму регистрации
                HomeScreen(
                    name: $name,
                    password: $password,
                    confirm: $confirm,
                    signedUp: $signedUp
                )
            }
            
            Spacer()
        }
        .padding(.horizontal, 59)
        .background(BackgroundView())
    }
}

struct RegisterView_Previews: PreviewProvider {
    static var previews: some View {
        MainScreen()
    }
}
