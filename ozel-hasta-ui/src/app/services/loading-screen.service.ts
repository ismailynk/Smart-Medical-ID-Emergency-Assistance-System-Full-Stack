import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class LoadingScreenService {

    private isloading = false;
    loadingStatus: Subject<boolean> = new Subject();

    get loading() {
        return this.isloading;
    }

    set loading(value) {
        this.isloading = value;
        this.loadingStatus.next(value);
    }

    startLoading() {
        this.loading = true;
    }

    stopLoading() {
        this.loading = false;
    }
}
