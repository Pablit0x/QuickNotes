//
//  NoteDetailViewModel.swift
//  iosApp
//
//  Created by Paweł Szymański on 18/02/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension NoteDetailScreen{
    @MainActor class NoteDetailViewModel: ObservableObject {
        private var noteDataSource: NoteDataSource?
        
        private var noteId: Int64? = nil
        @Published var noteTitle = ""
        @Published var noteContent = ""
        @Published var noteColor = Note.Companion().getRandomColor()
        
        init(noteDataSource: NoteDataSource?){
            self.noteDataSource = noteDataSource
        }
        
        func loadNoteIfExists(id: Int64?) {
            if id != nil {
                self.noteId = id
                noteDataSource?.getNoteById(noteId: id!, completionHandler: {note, error in
                    self.noteTitle = note?.title ?? ""
                    self.noteContent = note?.content ?? ""
                    self.noteColor = note?.colorHex ?? Note.Companion().getRandomColor()
                })
            }
        }
        
        func changeColor(newColor : Int64){
            noteColor = newColor
        }
        
        func saveNote(onSaved: @escaping () -> Void){
            noteDataSource?.insertNote(note: Note(id: noteId == nil ? nil : KotlinLong(value: noteId!), title: self.noteTitle, content: self.noteContent, colorHex: self.noteColor, created: DateTimeUtil().now()), completionHandler: { error in
                onSaved()
            })
        }
    
        func isUpdatedNoteNotSaved(title: String, content: String, color: Int64, completionHandler: @escaping (Bool) -> Void) {
            if noteId == nil {
                if !title.isEmpty || !content.isEmpty {
                    completionHandler(true)
                } else {
                    completionHandler(false)
                }
            } else {
                noteDataSource?.getNoteById(noteId: noteId!, completionHandler: { note, error in
                    if let note = note {
                        if note.title != title || note.content != content || note.colorHex != color {
                            completionHandler(true)
                        } else {
                            completionHandler(false)
                        }
                    } else {
                        completionHandler(false)
                    }
                })
            }
        }




        
        func setParamsAndLoadNote(noteDataSource: NoteDataSource, noteId : Int64?){
            self.noteDataSource = noteDataSource
            loadNoteIfExists(id: noteId)
        }
    }
}
