
import SwiftUI
import shared

struct HomeScreen: View {
    let networkManager = NetworkManager.shared
    @Binding var name: String
    @Binding var password: String
    @Binding var confirm: String
    
    @State private var errorDescription = ""
    @State private var showAlert = false
    
    @Binding var signedUp: Bool
    
    var body: some View {
        VStack(spacing: 0) {
            Text("Регистрация")
                .font(.system(size: 22))
                .padding(.bottom, 12)
            
            VStack(spacing: 8.5) {
                AppTextField(
                    title: "Имя",
                    text: $name
                )
                AppTextField(
                    title: "Пароль",
                    text: $password
                )
                AppTextField(
                    title: "Введите пароль еще раз",
                    text: $confirm
                )
            }
            .padding(.bottom, 28.75)
            
            Button {
                // Валидация полей
                guard !name.isEmpty, !password.isEmpty, !confirm.isEmpty else {
                    showAlert = true
                    errorDescription = "Все поля должны быть заполнены!"
                    return
                }
                
                guard password == confirm else {
                    showAlert = true
                    errorDescription = "Пароли должны совпадать!"
                    return
                }
                
//                // Отправка запроса на регистрацию
//                networkManager.registerUser(name: name, password: password) { success, error in
//
//                    // Если возникли ошибки при ответе на запрос
//                    if let error = error {
//                        // Задаем описание ошибки для нашего всплывающего предупреждения
//                        if let apiError = error as? ApiError {
//                            errorDescription = apiError.description
//                        } else {
//                            errorDescription = error.localizedDescription
//                        }
//
//                        // И говорим, что будем показывать алерт
//                        showAlert = true
//                    }

                    // А если все прошло успешно, устанавливаем флажок signedUp на true
//                    if success {
//                        showAlert = false
//                        signedUp = true
//                    }
//                }
                signedUp = true
                
            } label: {
                Text("Зарегистрироваться")
                    .padding()
                    .background(.white)
                    .foregroundColor(.black)
            }
        }
        .alert(isPresented: $showAlert) {
            Alert(
                title: Text("Ошибка ⚠️"),
                message: Text(errorDescription)
            )
        }
        .padding(.horizontal, 25)
        .padding(.top, 58)
        .padding(.bottom, 116)
        .background(
            VisualEffect(style: .systemUltraThinMaterial)
                .opacity(0.8)
        )
        
    }
}
