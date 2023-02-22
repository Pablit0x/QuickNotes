//
//  NoteListScreen.swift
//  iosApp
//
//  Created by Paweł Szymański on 18/02/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct NoteListScreen: View {
    private var noteDataSource: NoteDataSource
    @StateObject var viewModel = NoteListViewModel(noteDataSource: nil)
    
    @State private var isNoteSelected = false
    @State private var selectedNoteId: Int64? = nil
    
    init(noteDataSource: NoteDataSource) {
        self.noteDataSource = noteDataSource
    }
    
    var body: some View {
        VStack {
            ZStack {
                NavigationLink(destination: NoteDetailScreen(noteDataSource: self.noteDataSource, noteId: selectedNoteId), isActive: $isNoteSelected) {
                    EmptyView()
                }.hidden()
                SearchTextField<NoteDetailScreen>(onSearchToggled: {
                    viewModel.toggleSearch()
                }, destinationProvider: {
                    NoteDetailScreen(
                        noteDataSource: noteDataSource,
                        noteId: nil
                    )
                }, isSearchActive: viewModel.isSearchActive, searchText: $viewModel.searchText)
                .frame(maxWidth: .infinity, minHeight: 40)
                .padding()
                
                if !viewModel.isSearchActive {
                    Text("All notes")
                        .font(.custom(
                             "AmericanTypewriter",
                             fixedSize: 36))
                }
            }
            
            List {
                ForEach(viewModel.filteredNotes.sorted(by: {
                    DateTimeUtil().toEpochMillis(dateTime: $0.created ) >  DateTimeUtil().toEpochMillis(dateTime: $1.created )}),
                        id: \.self.id) { note in
                    Button(action: {
                        isNoteSelected = true
                        selectedNoteId = note.id?.int64Value
                    }) {
                        NoteItem(note: note, onDeleteClick: {
                            viewModel.deleteNoteById(id: note.id?.int64Value)
                        })
                    }
                    
                }
            }
            .onAppear {
                viewModel.loadNotes()
            }
            .listRowSeparator(.hidden)
            .listStyle(.plain)
        }
        .onAppear {
            viewModel.setNoteDataSource(noteDataSource: noteDataSource)
        }
        
    }
}

