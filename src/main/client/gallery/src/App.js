import './App.css';
import plus from './plus.png';
import React from 'react';
import Dropzone from 'react-dropzone';
import axios from 'axios';


const uploadCallback = acceptedFiles => {
  console.log(acceptedFiles);
  const file = new FormData();
  file.append('file',acceptedFiles[0]);
  axios.post('http://localhost:8080/photos/upload',file,{
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  }).then(response => {
    console.log(response);
  }).catch(error => {
    console.log(error);
  })
}

function App() {

  return (
    <div className="App">
      <Dropzone onDrop={acceptedFiles => uploadCallback(acceptedFiles)}>
        {({getRootProps, getInputProps}) => (
          <section>
            <div {...getRootProps()}>
              <input {...getInputProps()} />
              <div className="upload" >
                <div className="plus">
                <img src={plus} alt="click to upload"></img>
                </div>
              </div>
            </div>
          </section>
        )}
      </Dropzone>
    </div>
  );
}

export default App;
