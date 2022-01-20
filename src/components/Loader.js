import * as React from 'react';
import { makeStyles, createStyles } from '@mui/styles';
import Backdrop from '@mui/material/Backdrop';
import Loader from 'react-loader-spinner';

const useStyles = makeStyles((theme) => createStyles({
    backdrop: {
        backgroundColor: 'rgba(7, 33, 43, .1)',
        zIndex: 999,
    },
    screenLoader: {
        position: 'fixed',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
    },
}));

const AppLoader = (props) => {
    const { color, style } = props;
    const classes = useStyles();
    const finalColor = color || '#428BCA';

    return (
        <Backdrop open className={classes.backdrop}>
            <div className={classes.screenLoader} style={style}>
                <Loader loading color={finalColor} size={17} type={"MutatingDots"}/>
            </div>
        </Backdrop>
    );
};

export default AppLoader;
