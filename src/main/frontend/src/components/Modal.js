import React, {useState} from 'react';
import '../css/Modal.css';
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
    // 열기, 닫기, 모달 헤더 텍스트를 부모로부터 받아옴
    const { open, close, login } = props;

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


    const message = "스프링부트로 보내는 메세지";
    /*id: id,
        password: password,
        email: email,*/
    const join = () => {
        try {
            const id = document.getElementById("id").value;
            const password = document.getElementById("password").value;
            const email = document.getElementById("email").value;
            const config = {"Content-Type": 'application/json'};
            axios.post('/user/join', {
                id: id,
                password: password,
                email: email,
            } , config).then((response) => {
                console.log(response.data);
                close();
            });
        } catch (error) {
            console.error('Error fetching data : ' + error);
        }
    }

    if(open && loging === "1"){ //1은 로그인
        return (
            // 모달이 열릴때 openModal 클래스가 생성된다.
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
    }else if (open && loging === "2"){ // 2는 회원가입
        return (
            // 모달이 열릴때 openModal 클래스가 생성된다.
            <div className={open ? 'openModal modal' : 'modal'}>
                <section>
                    <main>
                        <h3>Join</h3>
                        <br/>
                        이메일 : <input id="email" type="text" style={{margin:'10px'}} />
                        <br/>
                        아이디 : <input id="id" type="text" style={{margin:'10px'}} />
                        <br/>
                        패스워드 : <input id="password" type="text" />

                    </main>
                    <footer>
                        <button className="join"  onClick={join} >
                            Join
                        </button>
                        <button className="login" onClick={nowLogin} >
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