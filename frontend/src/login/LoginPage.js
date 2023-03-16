import axios from "axios";
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './LoginPage.css'

const LoginPage = () =>
{
    let navigate = useNavigate();

    var [usernameIntroduced, setUsernameIntroduced] = useState("");
    var [passwordIntroduced, setPasswordIntroduced] = useState("");

    function loginUser()
    {
        let loginDTO = {
            username: usernameIntroduced,
            password: passwordIntroduced
        }

        axios.post(process.env.REACT_APP_SERVER_URL + '/login', loginDTO)
        .then(
            response =>
            {
                if(response.data.role === 'ADMIN')
                    navigate('/admin', {state:response.data});
                else
                    navigate('/user', {state:response.data});
            }
        )
        .catch(
            function(error)
            {
                console.log("loginUser function - error while logging in");
                console.log(error);
            }
        )
    }


    return(
        <div className="bg-slate-100 min-h-screen space-y-7">
            
            <br></br><br></br><br></br><br></br><br></br><br></br><br></br><br></br><br></br>
            <p className="page-title">Welcome to Online Energy Monitoring Platform</p>
            <h5 className="font-poppins text-center">Get back into your account</h5>

            <div className="form-box">
                <form className="flex flex-col justify-center items-center space-y-5">
                    <input placeholder=" username, e.g sorina13" required className="input-box" value={usernameIntroduced} 
                    onChange={e => setUsernameIntroduced(e.target.value)}/>
                    <input placeholder=" •••••••••" type="password" required className="input-box" value={passwordIntroduced} 
                    onChange={e => setPasswordIntroduced(e.target.value)}/>
                </form>
                <br></br>
                <div className="flex flex-row justify-center items-center space-x-6">
                    <button className="login-button" onClick={loginUser}>Login</button>
                    <button className="register-button1" onClick={() => {navigate('/register');}}>Register</button>
                </div>

            </div>

        </div>
    )
};

export default LoginPage