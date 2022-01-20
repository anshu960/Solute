import { Avatar, Box, } from '@mui/material';
import React from 'react';
//dropzone
import Dropzone from 'react-dropzone';
import { useDispatch } from 'react-redux';
import { uploadBusinessImage } from '../../store/fileUpload';

export default function BusinessProfileImage({files, setFiles}) {
    const dispatch = useDispatch()
    const handleSelectedFile = (acceptedFiles) => {
        if (acceptedFiles.length) {
            setFiles(acceptedFiles);
            acceptedFiles.forEach(file => {
                dispatch(uploadBusinessImage(file))    
            });
        }
    };
    const getImage = () => files.length && URL.createObjectURL(files[0]);
    return(<Dropzone onDrop={acceptedFiles => handleSelectedFile(acceptedFiles)} accept="image/*" multiple={false}>
        {({ getRootProps, getInputProps }) => {
            return(
                <Box mt={2} {...getRootProps()}>
                    <input {...getInputProps()} />
                    <Avatar
                    src={getImage()}
                    sx={{
                        mx: 'auto',
                        borderWidth: 2,
                        borderStyle: 'solid',
                        borderColor: 'common.white',
                        width: { xs: 80, md: 128 },
                        height: { xs: 80, md: 128 },
                    }}
                />
                </Box>
            )}}
    </Dropzone>)
}