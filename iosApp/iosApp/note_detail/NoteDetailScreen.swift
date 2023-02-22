//
//  NoteDetailScreen.swift
//  iosApp
//
//  Created by Paweł Szymański on 18/02/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared


struct NoteDetailScreen: View {
    private var noteDataSource: NoteDataSource
    private var noteId: Int64? = nil
    @StateObject var viewModel = NoteDetailViewModel(noteDataSource: nil)
    @Environment(\.presentationMode) var presentation
    @State private var isColorPickerOpen : Bool = false
    @State var showingAlert : Bool = false
    
    
    
    init(noteDataSource: NoteDataSource, noteId: Int64? = nil) {
        self.noteDataSource = noteDataSource
        self.noteId = noteId
    }
    
    private let colors = Colors()
    
    var body: some View {
        VStack(alignment: .leading){
            TextField("Title", text: $viewModel.noteTitle)
                .font(.title)
            Divider().foregroundColor(Color.secondary)
            TextField("Start typing...", text: $viewModel.noteContent,  axis: .vertical).lineLimit(5...10)
            Spacer()
            HStack{
                Spacer()
                NoteColorSelector(
                    primaryItem: ExpandableButtonItem(label: "✗", color: .forestGreen), // No action
                    secondaryItems: [
                        ExpandableButtonItem(color: .forestGreen){
                            viewModel.changeColor(newColor: colors.ForestGreenHex)
                            withAnimation{
                                isColorPickerOpen.toggle()
                            }
                        },
                        ExpandableButtonItem(color: .darkBlue){
                            viewModel.changeColor(newColor: colors.DarkBlueHex)
                            withAnimation{
                                isColorPickerOpen.toggle()
                            }
                        },
                        ExpandableButtonItem(color: .pinkRed){
                            viewModel.changeColor(newColor: colors.PinkRedHex)
                            withAnimation{
                                isColorPickerOpen.toggle()
                            }
                        },
                        ExpandableButtonItem(color: .peach){
                            viewModel.changeColor(newColor: colors.OrangeHex)
                            withAnimation{
                                isColorPickerOpen.toggle()
                            }
                        },
                        ExpandableButtonItem(color: .orchid){
                            viewModel.changeColor(newColor: colors.PurpleHex)
                            withAnimation{
                                isColorPickerOpen.toggle()
                            }
                        }
                    ], currentColorHex: $viewModel.noteColor, isExpanded:$isColorPickerOpen
                )
            }
        }.toolbar(content: {
            
            Button(action : {
                viewModel.saveNote {
                    presentation.wrappedValue.dismiss()
                }
            }){
                Image(systemName: "checkmark")
            }
        })
        .padding()
        .onAppear{
            viewModel.setParamsAndLoadNote(noteDataSource: noteDataSource, noteId: noteId)
        }
        .navigationBarTitle(Text(noteId == nil ? "Create Note" : "Edit Note"), displayMode: .inline)
        .navigationBarBackButtonHidden(true)
        .navigationBarItems(leading:
                                Button(action: {
            viewModel.isUpdatedNoteNotSaved(title: viewModel.noteTitle, content: viewModel.noteContent, color: viewModel.noteColor) { Bool in
                if(Bool){
                    self.showingAlert = true
                } else {
                    presentation.wrappedValue.dismiss()
                }
            }
        }) {
            HStack {
                Image(systemName: "chevron.left")
                Text("Back")
            }
        }
        )
        .alert(isPresented: $showingAlert){
            Alert(
                title: Text("Unsaved Changes"),
                message: Text("Unsaved changes detected. Save before quitting?"),
                primaryButton: .destructive(Text("No")){
                    presentation.wrappedValue.dismiss()
                },
                secondaryButton: .cancel(Text("Yes")){viewModel.saveNote{presentation.wrappedValue.dismiss()}}
            )
        }
    }
}

