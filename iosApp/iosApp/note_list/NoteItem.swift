//
//  NodeItem.swift
//  iosApp
//
//  Created by Paweł Szymański on 18/02/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct NoteItem: View {
    var note: Note
    var onDeleteClick: () -> Void
    
    var body: some View {
        VStack(alignment: .leading){
            HStack{
                Text(note.title)
                    .font(.title3)
                    .fontWeight(.semibold)
                Spacer()
                Button(action: onDeleteClick){
                    Image(systemName: "xmark")
                        .foregroundColor(.secondaryVariant)
                }
            }.padding(.bottom, 3)
            
            Text(note.content)
                .fontWeight(.light)
                .padding(.bottom, 3)
            
            HStack{
                Spacer()
                Text(DateTimeUtil().formatNoteDate(dateTime: note.created))
                    .foregroundColor(.secondaryVariant)
                    .font(.footnote)
                    .fontWeight(.light)
            }
        }
        .padding()
        .overlay(
            RoundedRectangle(cornerRadius: 25)
                .stroke(Color(hex : note.colorHex), lineWidth: 5)
        )
        .gradientSurface()
        .cornerRadius(25)
    }
}


struct NodeItem_Previews: PreviewProvider {
    static var previews: some View {
        NoteItem(
            note: Note(id: nil, title: "My notes", content: "CONTENT!", colorHex: 0xFF2291, created: DateTimeUtil().now()), onDeleteClick: {})
    }
}
