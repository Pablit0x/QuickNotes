//
//  SearchTextField.swift
//  iosApp
//
//  Created by Paweł Szymański on 18/02/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct SearchTextField <Destination: View> : View {
    
    var onSearchToggled : () -> Void
    var destinationProvider: () -> Destination
    var isSearchActive: Bool
    @Binding var searchText : String
    
    var body: some View {
        HStack {
            TextField("Search....", text : $searchText)
                .textFieldStyle(.roundedBorder)
                .opacity(isSearchActive ? 1 : 0)
            if !isSearchActive {
                Spacer()
            }
            Button(action: onSearchToggled){
                Image(systemName: isSearchActive ? "xmark" : "magnifyingglass").tint(.onBackground).padding(.trailing, 4)
            }
            NavigationLink(destination: destinationProvider()) {
                Image(systemName: "plus").tint(.onBackground)
            }
        }
    }
}

struct SearchTextField_Previews: PreviewProvider {
    static var previews: some View {
        SearchTextField(
            onSearchToggled: {},
            destinationProvider: {EmptyView()},
            isSearchActive: true,
            searchText: .constant("Search Box..."))
    }
}
