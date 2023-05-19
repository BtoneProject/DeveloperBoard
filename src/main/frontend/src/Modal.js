import './Modal.css';
import React, {useState} from 'react';
import axios from "axios";


const submitLogin = async () => {
    const data = {
        "id": document.getElementById("id").value,
        "password": document.getElementById("password").value
    };

    try {
        const response = await axios.post('/user/login', data);
        console.log(response);
    } catch (error) {
        console.error('Error fetching data : ' + error);
    }
}

const Modal = (props) => {
    const {open, close, login} = props;
    const [loging, setloging] = useState(login);

    const nowLogin = () => {
        setloging("1");
    };
    const nowJoin = () => {
        setloging("2");
    };
    const reset = () => {
        close();
        setloging("1");
    }

    if (open && loging === "1") { //1은 로그인
        return (
            <div className={open ? 'openModal modal' : 'modal'}>
                <section>
                    <main>
                        <h3>Login</h3>
                        <br/>
                        아이디 : <input className="id" id="id" type="text" style={{margin: '10px'}}/>
                        <br/>
                        패스워드 : <input className="pwd" id="password" type="text"/>
                    </main>
                    <footer>
                        <button className="join" onClick={nowJoin}>
                            Join
                        </button>
                        <button className="login" onClick={submitLogin}>
                            Login
                        </button>
                        <button onClick={reset}>
                            close
                        </button>
                    </footer>
                </section>
            </div>
        );
    } else if (open && loging === "2") {
        return (
            <div className={open ? 'openModal modal' : 'modal'}>
                <section>
                    <main>
                        <h3>Join</h3>
                        <br/>
                        아이디 : <input className="id" type="text" style={{margin: '10px'}}/>
                        <br/>
                        패스워드 : <input className="pwd" type="text"/>

                    </main>
                    <footer>
                        <button className="join">
                            Join
                        </button>
                        <button className="login" onClick={nowLogin}>
                            Login
                        </button>
                        <button onClick={reset}>
                            close
                        </button>
                    </footer>
                </section>
            </div>
        );
    }
};

export default Modal;