import { Grid, Checkbox, Typography, FormControlLabel } from '@mui/material';
import PropTypes from 'prop-types';
import React, { forwardRef, useImperativeHandle, useState } from 'react';

const CheckboxField = forwardRef((props, ref) => {
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
            <FormControlLabel
                control={(
                <Checkbox
                    variant="outlined"
                    id={props.id}
                    name={props.name}
                    checked={props.checked}
                    onChange={onChange}
                    onBlur={validateField}
                    onFocus={resetErrorCode}
                    {...props}
                />
                )}
                label={props.label}
                >
            </FormControlLabel>
            {errorHTML}
            </div>
        </React.Fragment>
    );
});

CheckboxField.propTypes = {
    name: PropTypes.string.isRequired,
    id: PropTypes.string,
    checked: PropTypes.bool,
    validationType: PropTypes.oneOf([
        '', 'mandatory',
    ]),
    onChange: PropTypes.func,
    setErrorValue: PropTypes.func,
};

CheckboxField.defaultProps = {
    value: '',
    validationType: '',
};

export default CheckboxField;
