package cu.gob.ith.presentation.activities.main.fragments.menu.viewmodel;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MenuFragmentViewModel extends ViewModel {
    private boolean isLoadedData;

    @Inject
    public MenuFragmentViewModel() {
    }

    public boolean isLoadedData() {
        return isLoadedData;
    }

    public void setLoadedData(boolean loadedData) {
        isLoadedData = loadedData;
    }
}
