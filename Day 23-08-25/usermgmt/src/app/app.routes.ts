import { Routes } from '@angular/router';
import { Userlist } from './userlist/userlist';
import { Useradd } from './useradd/useradd';
import { Useredit } from './useredit/useredit';
import { Userdelete } from './userdelete/userdelete';

export const routes: Routes = [
  { path: '', component: Userlist },
 { path: 'userlist', component: Userlist },
 { path: 'useradd', component: Useradd },
{ path: 'useredit/:id', component: Useredit },
{ path: 'userdelete/:id', component: Userdelete }

];
