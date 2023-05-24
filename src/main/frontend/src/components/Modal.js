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

    const [logging, setlogging] = useState(login);

    // const id = (e) => {e.target.value;}
    // const password = (e) => {e.target.value;}
    // const email = (e) => {e.target.value;}
    const [id, setid] = useState("");
    const [idStatus, setidStatus] = useState(false);
    const [email, setemail] = useState("");
    const [emailStatus, setemailStatus] = useState(false);
    const [password, setpassword] = useState("");
    const [passwordStatus, setpasswordStatus] = useState(false);

    // 아이디 값 가져오면서 validation 체크
    const onChangeid = (e) => {
        const idRegex = /^[A-Za-z0-9+]{5,12}$/;   // id 정규식 검사 대소문자 , 숫자 , 5자리 이상 12자리 이하 특수문자금지
        const soild = e.target.value;
        setid(e.target.value);
        if(soild  && (idRegex.test(soild))){
            setidStatus(true);
        }else{
            setidStatus(false);
        }
    }

    // 비밀번호 값 가져오면서 validation 체크
    const onChangepwd = (e) => {
        // 비밀번호 조건 : 최소 8글자 , 최대 14글자 , 글자 1개, 숫자 1개, 특수문자 1개
        const pwdRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,14}$/;
        const soild = e.target.value;
        setpassword(e.target.value);
        if(soild  && (pwdRegex.test(soild))){
            setpasswordStatus(true);
        }else{
            setpasswordStatus(false);
        }
    }

    // 이메일 값 가져오면서 validation 체크
    const onChangeemail = (e) => {
        // 비밀번호 조건 : 최소 8글자 , 최대 14글자 , 글자 1개, 숫자 1개, 특수문자 1개
        const emailRegex = /^[a-z0-9]+@[a-z]+\.[a-z]{2,3}$/;
        const soild = e.target.value;
        setemail(e.target.value);
        if(soild  && (emailRegex.test(soild))){
            setemailStatus(true);
        }else{
            setemailStatus(false);
        }
    }

    const duplicate = async () => {
        await axios.post('/user/duplicate', {
            id: id,
        }).then((response) => {
            console.log(response.data);
        });
    }


    const config = {"Content-Type": 'application/json'};

    const kakaoAuthReqUrl = "https://kauth.kakao.com/oauth/authorize?response_type=code"
        + "&client_id=48f96d8bb3a053ea613cb4ff71f25c10"
        + "&redirect_uri=http://localhost:8080/auth/kakao/callback"

    const nowLogin = () => {
        setlogging("1");
    };
    const nowJoin = () => {
        setlogging("2");
    };

    const reset = () => {
        close();
        setlogging("1");
    }


    const validation = () => {
        if(idStatus && emailStatus && passwordStatus) return true;
        else return false;
    }
    /*id: id,
        password: password,
        email: email,*/
    const join = () => {
        // validation 체크.
        if(validation()) {
            try {
                const config = {"Content-Type": 'application/json'};
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
        }else{
            alert("이메일 , 아이디 , 패스워드 형식에 맞게 작성해주세요.");
        }
    }



    if(open && logging === "1"){ //1은 로그인
        return (
            // 모달이 열릴때 openModal 클래스가 생성된다.
            <div className={open ? 'openModal modal' : 'modal'}>
                <section>
                    <main>
                        <h3>Login</h3>
                        <br/>
                        아이디 : <input className="id" id="id" type="text" style={{margin: '10px'}}/>
                        <br/>
                        패스워드 : <input className="pwd" id="password" type="password"/>
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
    }else if (open && logging === "2"){ // 2는 회원가입
        return (
            // 모달이 열릴때 openModal 클래스가 생성된다.
            <div className={open ? 'openModal modal' : 'modal'}>
                <section>
                    <main>
                        <h3>Join</h3>
                        <br/>
                        이메일 : <input id="email" type="text" style={{margin:'10px'}} onChange={onChangeemail} />
                        {!emailStatus && email != "" && <div class="invalid-input" style={{color: "red"}}>이메일 형식에 맞게 작성해주세요.</div>}
                        <br/>
                        아이디 : <input id="id" type="text" style={{margin:'10px'}} onChange={onChangeid} />
                        <br/>
                        <button style={{margin: '10px'}} onClick={duplicate}>아이디 중복 확인</button>
                        {!idStatus && id != "" &&  <div class="invalid-input" style={{color: "red"}}>아이디는 5~12자리이며 <br/> 대소문자와 숫자만 사용 가능합니다.</div>}
                        <br/>
                        패스워드 : <input id="password" type="password" onChange={onChangepwd} />
                        {!passwordStatus && password != "" && <div class="invalid-input" style={{color: "red"}}>비밀번호는 8~14자리이며 <br/> 글자, 숫자, 특수문자가 하나씩 포함되어야 합니다.</div>}
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