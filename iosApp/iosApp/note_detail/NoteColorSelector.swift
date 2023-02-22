//
//  NoteColorSelector.swift
//  iosApp
//
//  Created by Paweł Szymański on 19/02/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct NoteColorSelector: View {
    
    var primaryItem: ExpandableButtonItem
    let secondaryItems: [ExpandableButtonItem]
    @Binding var currentColorHex : Int64
    @Binding var isExpanded : Bool
    
    private let noop: () -> Void = {}
    private let size: CGFloat = 48
    private var cornerRadius: CGFloat {
        get { size / 2 }
    }
    private let shadowColor = Color.black.opacity(0.4)
    private let shadowPosition: (x: CGFloat, y: CGFloat) = (x: 2, y: 2)
    private let shadowRadius: CGFloat = 3
    
    var body: some View {
        VStack {
            ForEach(secondaryItems) { item in
                Button(item.label ?? " ", action: item.action ?? self.noop)
                    .frame(
                        width: self.isExpanded ? self.size : 0,
                        height: self.isExpanded ? self.size : 0)
                    .background(item.color)
                    .cornerRadius(cornerRadius)
            }
            
            Button(self.isExpanded ? primaryItem.label ?? " " : " " , action: {
                withAnimation {
                    self.isExpanded.toggle()
                }
            })
            .frame(width: size, height: size)
            .background(self.isExpanded ? Color(UIColor.systemBackground) : Color(hex: currentColorHex))
            .overlay(
                RoundedRectangle(cornerRadius: cornerRadius)
                    .stroke(Color.onSurface, lineWidth: !isExpanded ? 1 : 0)
            )
            .cornerRadius(cornerRadius)
            .tint(.onBackground)
        }
        .cornerRadius(cornerRadius)
        .font(.title.bold())
        
    }
}

struct ExpandableButtonItem: Identifiable {
    let id = UUID()
    var label: String? = nil
    let color: Color
    private(set) var action: (() -> Void)? = nil
    
}

