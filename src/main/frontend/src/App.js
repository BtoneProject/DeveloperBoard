// src/main/frontend/src/App.js

import './css/App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter,Routes,Route} from 'react-router-dom';
import React, {useEffect, useState} from 'react';
import Modal from "./components/Modal";
import axios from 'axios';
import Header from './components/Header';
import MyInfo from './components/UserInfo';
import Home from './components/Home';
import * as PropTypes from "prop-types";

BrowserRouter.propTypes = {children: PropTypes.node};

function App() {
    return (
        <BrowserRouter>
        <div className="App">
            <Header></Header>
        </div>
            <Routes>
                <Route path="/" element={<Home/>}></Route>
                <Route path="/userInfo" element={<MyInfo/>}></Route>
            </Routes>
        </BrowserRouter>
    );
}

export default App;