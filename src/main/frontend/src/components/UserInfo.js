import React, {useEffect, useState} from 'react';
import {Button, Col, Container, Form, Row} from 'react-bootstrap';
import axios from "axios";

const UserInfo = () => {
    // 가상의 로그인 사용자 데이터
    const loggedInUser = {
        idNo: 1,
        id:'',
        name: '',
        email: '',
        introduce: '',
    };
    const [user, setUser] = useState([]);

    useEffect(() => {
        // 사용자 정보 가져오기
        const fetchUserData = async () => {
            try {
                const response = await axios.post('/user/myInfo', loggedInUser);
                setUser(response.data); // 응답에서 사용자 정보를 가져와 상태를 업데이트합니다.
                console.log(response.data);
            } catch (error) {
                console.error('사용자 정보를 가져오는 중에 오류가 발생했습니다:', error);
            }
        };

        fetchUserData(); // 사용자 정보 가져오기 함수 호출
    }, []);
    console.log(loggedInUser);


    return (
        <Container>
            <h1>회원 정보</h1>
            <br/>
            <Form>
                <Form.Group as={Row} className="mb-3" controlId="formPlaintextName">
                    <Form.Label column="lg" lg={2}>
                        이름
                    </Form.Label>
                    <Col sm="10">
                        <Form.Control plaintext readOnly defaultValue={user.id}/>
                    </Col>
                </Form.Group>
                <Form.Group as={Row} className="mb-3" controlId="formPlaintextEmail">
                    <Form.Label column="lg" lg={2}>
                        Email
                    </Form.Label>
                    <Col sm="10">
                        <Form.Control plaintext readOnly defaultValue={user.email}/>
                    </Col>
                </Form.Group>
                <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
                    <Form.Label column="lg" lg={2}>
                        한 줄 소개
                    </Form.Label>
                    <Col sm="10">
                        <Form.Control as="textarea" rows={3} readOnly defaultValue={user.introduce}/>
                    </Col>
                </Form.Group>
                <Form.Group as={Row} className="mb-3" controlId="formPlaintextEmail">
                    <Form.Label column="lg" lg={2}>
                        회원 탈퇴
                    </Form.Label>
                    <Col sm="10">
                        <Button variant="danger"> 회원 탈퇴</Button>
                    </Col>
                </Form.Group>
            </Form>
        </Container>
    );
};

export default UserInfo;