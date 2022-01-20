import { Avatar, Box, } from '@mui/material';
import React from 'react';
//dropzone
import Dropzone from 'react-dropzone';

export default function ProfileImage({files, setFiles}) {
    const handleSelectedFile = (acceptedFiles) => {
        if (acceptedFiles.length) {
            setFiles(acceptedFiles);
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