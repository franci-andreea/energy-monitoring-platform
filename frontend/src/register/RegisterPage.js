import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import './RegisterPage.css'

const RegisterPage = () => 
{
    let navigate = useNavigate();

    var [nameIntroduced, setNameIntroduced] = useState("");
    var [ageIntroduced, setAgeIntroduced] = useState("");
    var [addressIntroduced, setAddressIntroduced] = useState("");
    var [usernameIntroduced, setUsernameIntroduced] = useState("");
    var [passwordIntroduced, setPasswordIntroduced] = useState("");

    function registerUser()
    {
        let registerDTO = {
            name: nameIntroduced,
            address: addressIntroduced,
            age: ageIntroduced,
            username: usernameIntroduced,
            password: passwordIntroduced,
        }

        axios.post(process.env.REACT_APP_SERVER_URL + '/register', registerDTO)
        .then(
            response => 
            {
                console.log(response.data);
                alert("Successfully registered your account!");
                navigate('/');
            }
        )
        .catch(
            function(error)
            {
                console.log("register user function - an error occurred");
                console.log(error);
            }
        )
    }

    return(
        <div className="bg-slate-100 min-h-screen space-y-3">
            
            <br></br><br></br><br></br><br></br><br></br><br></br><br></br>
            <p className="page-title">Welcome to Online Energy Monitoring Platform</p>
            <br></br>
            <h5 className="font-poppins text-center">Create a new account</h5>
            <h6 className="font-poppins text-center">* mandatory field</h6>

            <div className="form-box font-poppins">
                <form className="flex flex-col items-center justify-center space-y-5">

                    <div className="flex flex-col">
                        <label>Name *</label>
                        <input placeholder=" John Doe" className="input-box" value={nameIntroduced} 
                            onChange={e => setNameIntroduced(e.target.value)}/>
                    </div>
                    
                    <div className="flex flex-col">
                        <label>Age *</label>
                        <input placeholder=" introduce a number" className="input-box" value={ageIntroduced} 
                            onChange={e => setAgeIntroduced(e.target.value)}/>
                    </div>
                   
                    <div className="flex flex-col">
                        <label>Address *</label>
                        <input placeholder=" city, street, no." className="input-box" value={addressIntroduced} 
                            onChange={e => setAddressIntroduced(e.target.value)}/>
                    </div>

                    <div className="flex flex-col">
                        <label>Username *</label>
                        <input placeholder=" e.g john15" className="input-box" value={usernameIntroduced} 
                            onChange={e => setUsernameIntroduced(e.target.value)}/>
                    </div>

                    <div className="flex flex-col">
                        <label>Password *</label>
                        <input placeholder=" •••••••••" type="password" className="input-box" value={passwordIntroduced} 
                            onChange={e => setPasswordIntroduced(e.target.value)}/>
                    </div>

                </form>

                <br></br>
                <div className="flex flex-row justify-center items-center space-x-6">
                    <button className="back-button" onClick={() => {navigate('/');}}>←</button>
                    <button className="register-button" onClick={registerUser}>Register</button>
                </div>
            </div>

        </div>
    )
};

export default RegisterPage;