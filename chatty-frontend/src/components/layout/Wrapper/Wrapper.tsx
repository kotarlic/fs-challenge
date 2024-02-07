import React from 'react';
import classes from './Wrapper.module.scss';

interface WrapperProps {
  right?: boolean;
  children: JSX.Element | JSX.Element[];
}

const Wrapper: React.FC<WrapperProps> = ({ children, right }) => {
  return (
    <div className={`${classes['l-wrapper']} ${right && classes['l-wrapper--right']}`}>
      {children}
    </div>
  );
};

export default Wrapper;
