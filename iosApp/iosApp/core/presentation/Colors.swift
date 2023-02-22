//
//  Colors.swift
//  iosApp
//
//  Created by Paweł Szymański on 18/02/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

extension Color{
    init(hex: Int64, alpha: Double = 1){
        self.init(
            .sRGB,
            red: Double((hex >> 16) & 0xff) / 255,
            green: Double((hex >> 08) & 0xff) / 255,
            blue: Double((hex >> 00) & 0xff) / 255,
            opacity: alpha
        )
    }
    
    private static let colors = Colors()
    static let lightBlue = Color(hex: colors.LightBlue)
    static let lightBlueGrey = Color(hex: colors.LightBlueGrey)
    static let accentViolet = Color(hex: colors.AccentViolet)
    static let textBlack = Color(hex: colors.TextBlack)
    static let darkGrey = Color(hex: colors.DarkGrey)
    static let superLightGrey = Color(hex: colors.SuperLightGrey)
    static let normalGrey = Color(hex: colors.NormalGrey)
    static let darkSurfaceStart = Color(hex: colors.DarkSurfaceStart)
    static let darkSurfaceEnd = Color(hex: colors.DarkSurfaceEnd)
    
    static let forestGreen = Color(hex: colors.ForestGreenHex)
    static let darkBlue = Color(hex: colors.DarkBlueHex)
    static let peach = Color(hex: colors.OrangeHex)
    static let pinkRed = Color(hex: colors.PinkRedHex)
    static let orchid = Color(hex: colors.PurpleHex)
    
    static let primaryColor = Color(light: .accentViolet, dark: .accentViolet)
    static let onPrimary = Color(light: .white, dark: .white)
    static let secondaryColor = Color(light: .superLightGrey, dark: .normalGrey)
    static let onSecondary = Color(light: .textBlack, dark: .white)
    static let background = Color(light: .lightBlueGrey, dark: .darkGrey)
    static let onBackground = Color(light: .textBlack, dark: .white)
    static let surface = Color(light: .white, dark: .darkGrey)
    static let onSurface = Color(light: .textBlack, dark: .white)
    static let secondaryVariant = Color(light: .normalGrey, dark :.lightBlue)
}

private extension Color{
    init(light: Self, dark: Self){
            self.init(uiColor: UIColor(light: UIColor(light), dark: UIColor(dark)))
    }
}

private extension UIColor{
    convenience init(light: UIColor, dark: UIColor){
        self.init{ traits in
            switch traits.userInterfaceStyle{
            case .light, .unspecified:
                return light
            case .dark:
                return dark
            @unknown default:
                return light
            }
        }
    }
}
