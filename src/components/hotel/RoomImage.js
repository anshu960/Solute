import { Box, Button } from '@mui/material';
import React, { useState } from 'react';
import UploadIcon from '@mui/icons-material/Upload';
//dropzone
import Dropzone from 'react-dropzone';

export default function RoomImage({files, setFiles}) {
    const handleSelectedFile = (acceptedFiles) => {
        if (acceptedFiles.length) {
            setFiles(acceptedFiles);
        }
    };
    const getImage = (index) => URL.createObjectURL(files[index]);
    return(<Dropzone onDrop={acceptedFiles => handleSelectedFile(acceptedFiles)} accept="image/*" multiple={true}>
        {({ getRootProps, getInputProps }) => {
            return(
                <Box mt={2} {...getRootProps()}>
                    <input {...getInputProps()} />
                    <Button
                        style={{cursor:'pointer',width: '165px',}}
                        variant="outlined"
                        startIcon={<UploadIcon />}
                    >
                        Upload Image
                    </Button>
                    {files.map((ele, index) => <Box component="img" alt="Image" src={getImage(index)} sx={{width :200, height: 200, marginTop:3}}></Box>)}
                </Box>
            )}}
    </Dropzone>)
}