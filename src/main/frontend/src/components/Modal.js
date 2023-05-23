import React, {useState} from 'react';
import '../css/Modal.css';
import axios from "axios";

const Modal = (props) => {
    const {open, close, login} = props;
    const [loging, setloging] = useState(login);

    const config = {"Content-Type": 'application/json'};

    const kakaoAuthReqUrl = "https://kauth.kakao.com/oauth/authorize?response_type=code"
        + "&client_id=48f96d8bb3a053ea613cb4ff71f25c10"
        + "&redirect_uri=http://localhost:8080/auth/kakao/callback"

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

    const submitLogin = async () => {
        const data = {
            "id": document.getElementById("id").value,
            "password": document.getElementById("password").value
        };

        try {
            await axios.post('/user/login', data, config)
                       .then( (response) => {
                           console.log(response.data);
                           close();
                       });
        } catch (error) {
            console.error('Error fetching data : ' + error);
        }
    }

    const join = () => {
        try {
            const id = document.getElementById("id").value;
            const password = document.getElementById("password").value;
            const email = document.getElementById("email").value;

            axios.post('/user/join', {
                id: id,
                password: password,
                email: email,
            }, config).then((response) => {
                console.log(response.data);
                close();
            });
        } catch (error) {
            console.error('Error fetching data : ' + error);
        }
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
                        <a href={kakaoAuthReqUrl}><img src="/kakao_login_medium.png" style={{width:'73px', height:'37px'}}/></a>
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
    } else if (open && loging === "2") { // 2는 회원가입
        return (
            <div className={open ? 'openModal modal' : 'modal'}>
                <section>
                    <main>
                        <h3>Join</h3>
                        <br/>
                        이메일 : <input id="email" type="text" style={{margin: '10px'}}/>
                        <br/>
                        아이디 : <input id="id" type="text" style={{margin: '10px'}}/>
                        <br/>
                        패스워드 : <input id="password" type="text"/>

                    </main>
                    <footer>
                        <button className="join" onClick={join}>
                            Join
                        </button>
                        <button className="login" onClick={nowLogin}>
                            Login
                        </button>
                        <button className="close" onClick={reset}>
                            close
                        </button>
                    </footer>
                </section>
            </div>
        );
    }


};
export default Modal