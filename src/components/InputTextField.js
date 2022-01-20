import { Grid, TextField, Typography } from '@mui/material';
import PropTypes from 'prop-types';
import React, { forwardRef, useImperativeHandle, useState } from 'react';

const InputTextField = forwardRef((props, ref) => {
    const [error, setError] = useState(null);
    const { classes } = props;
    let errorHTML = null;
    /**
     * On Change event of the textbox.
     * @param  {Object} event [Event object]
     */
    const onChange = (event) => {
        props.onChange(event);
    };

    /**
     * Validates field on blur.
     */
    const validateField = () => {
        const {
            validationType, value, name,
        } = props;

        if (validationType) {
            const error = 'Invalid';//validateInput(validationType, value);
            setError(error);
            props.setErrorValue(`${name}`);
        }
    };

    /**
     * [Reset Errors]
     */
    const resetErrorCode = () => {
        setError(null);
        const { name } = props;

        if (props.setErrorValue) {
            props.setErrorValue(`${name}`);
        }
    };

    useImperativeHandle(ref, () => ({
        validateField,
        resetErrorCode,
    }));

    if (error || props.errorMessage) {
        errorHTML = (
            <Grid item md={12} lg={12} className={classes.alignCenter}>
                <Typography color="error" variant="subtitle2">{error ? error.errorMessage : props.errorMessage}</Typography>
            </Grid>
        );
    }

    return (
        <React.Fragment>
            <div>
                <TextField
                    fullWidth
                    multiline={props.multiline}
                    type={props.type}
                    placeholder={props.placeholder}
                    variant="outlined"
                    id={props.id}
                    name={props.name}
                    value={props.value}
                    onChange={onChange}
                    onBlur={validateField}
                    onFocus={resetErrorCode}
                    {...props}
                />
                {errorHTML}
            </div>
        </React.Fragment>
    );
});

InputTextField.propTypes = {
    type: PropTypes.string,
    name: PropTypes.string.isRequired,
    id: PropTypes.string,
    multiline: PropTypes.bool,
    placeholder: PropTypes.string,
    value: PropTypes.string,
    validationType: PropTypes.oneOf([
        '', 'mandatory', 'email', 'password',
    ]),
    onChange: PropTypes.func,
    setErrorValue: PropTypes.func,
};

InputTextField.defaultProps = {
    type: 'text',
    value: '',
    validationType: '',
};

export default InputTextField;
