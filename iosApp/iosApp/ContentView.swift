import SwiftUI
import shared

struct ContentView: View {
    
    var databaseModule : DatabaseModule

	var body: some View {
        ZStack{
            NoteListScreen(noteDataSource: databaseModule.noteDataSource)
        }
	}
}
