import SwiftUI

@main
struct iOSApp: App {
    init() {
        Koin.doInitKoin()
    }
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}