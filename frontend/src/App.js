import { BrowserRouter, Route, Routes } from 'react-router-dom';
import AdminPage from './administrator/AdminPage';
import ChatPage from './chat/ChatPage';
import EditDevicePage from './administrator/EditDevicePage';
import EditUserPage from './administrator/EditUserPage';
import ManageDevicesPage from './administrator/ManageDevicesPage';
import ManageUserPage from './administrator/ManageUsersPage';
import './App.css';
import LoginPage from './login/LoginPage';
import RegisterPage from './register/RegisterPage';
import SelectedDevicePage from './user/SelectedDevicePage';
import UserPage from './user/UserPage';

function App() {
  return (
    <BrowserRouter>
      <div>
          <Routes>
            <Route path='/*' element = {<LoginPage/>} />
            <Route path='/register/*' element = {<RegisterPage/>} />
            <Route path='/user/*' element = {<UserPage/>} />
            <Route path='/user/chat-page/*' element = {<ChatPage/>} />
            <Route path='/user/devices/*' element = {<SelectedDevicePage/>} />
            <Route path='/admin/*' element = {<AdminPage/>} />
            <Route path='/admin/manage-users/*' element = {<ManageUserPage/>} />
            <Route path='/admin/manage-users/edit/*' element = {<EditUserPage/>} />
            <Route path='/admin/manage-devices/*' element = {<ManageDevicesPage/>} />
            <Route path='/admin/manage-devices/edit/*' element = {<EditDevicePage/>} />
            <Route path='/admin/chat-page/*' element = {<ChatPage/>} />
          </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
