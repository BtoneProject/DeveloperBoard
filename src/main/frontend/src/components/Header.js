import React, {useState} from "react";
import Modal from "./Modal";
import { Link } from 'react-router-dom'

function Header() {
    const [modalOpen, setModalOpen] = useState(false);

    const openModal = () => {
        setModalOpen(true);
    };
    const closeModal = () => {
        setModalOpen(false);
    }

    return (
        <div className="home-header">
            <div className="home-title">
                <h1>
                    <Link to="/">DeveloperBoard</Link>
                </h1>
                <div className="user-info">
                    <React.Fragment>
                        <button onClick={openModal} className="btn btn-primary login">로그인</button>
                        <Modal open={modalOpen} close={closeModal} login="1"/>
                    </React.Fragment>
                    <Link to="/userInfo">
                        <button className="btn btn-primary login">내정보</button>
                    </Link>
                </div>
            </div>
        </div>
    );
}

export default Header;