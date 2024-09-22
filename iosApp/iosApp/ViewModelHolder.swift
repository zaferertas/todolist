import Foundation
import Shared

class ViewModelHolder<VM: ViewModel>: ObservableObject {

    private let key = String(describing: type(of: VM.self))
    private let viewModelStore = ViewModelStore()

    init(_ viewModel: VM = .init()) {
        viewModelStore.put(key: key, viewModel: viewModel)
    }

    var instance: VM {
        (viewModelStore.get(key: key) as? VM)!
    }

    deinit {
        viewModelStore.clear()
    }
}
