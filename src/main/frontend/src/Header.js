import React, {useState} from "react";
import Modal from "./Modal";

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
                <h1><a href="/">DeveloperBoard</a></h1>
                <div className="user-info">
                    <React.Fragment>
                        <button onClick={openModal} className="btn btn-primary login">로그인</button>
                        <Modal open={modalOpen} close={closeModal} login="1"/>
                    </React.Fragment>
                </div>
            </div>
        </div>
    );
}

export default Header;