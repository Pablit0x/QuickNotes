//
//  NoteListViewModel.swift
//  iosApp
//
//  Created by Paweł Szymański on 18/02/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension NoteListScreen {
    @MainActor class NoteListViewModel : ObservableObject{
        private var noteDataSource: NoteDataSource? = nil
        private var notes = [Note]()
        private let searchNotes = SearchNotes()
        @Published private (set) var isSearchActive = false
        @Published private (set) var filteredNotes = [Note]()
        @Published var searchText = "" {
            didSet{
                self.filteredNotes = searchNotes.execute(notes: self.notes, query: searchText)
            }
        }
        
        init(noteDataSource: NoteDataSource? = nil) {
            self.noteDataSource = noteDataSource
        }
        
        func loadNotes(){
            noteDataSource?.getAllNotes(completionHandler: {notes, error in
                self.notes = notes ?? []
                self.filteredNotes = self.notes
            })
        }
        
        func deleteNoteById(id: Int64?){
            noteDataSource?.deleteNoteById(noteId: id!, completionHandler: { error in
                self.loadNotes()
            })
        }
        
        func toggleSearch(){
            isSearchActive = !isSearchActive
            if !isSearchActive {
                searchText = ""
            }
        }
        
        func setNoteDataSource(noteDataSource: NoteDataSource){
            self.noteDataSource = noteDataSource
        }
    }
}
