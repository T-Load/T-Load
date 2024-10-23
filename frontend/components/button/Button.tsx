import { buttonColor, buttonColorType, buttonSize, buttonSizeType } from "types/button";

interface ButtonProps {
  size: buttonSizeType;
  color: buttonColorType;
  isLoading?: boolean;
  children: React.ReactNode;
  onClick: () => void;
}

const Button = ({ size, color, isLoading, children, onClick, ...rest }: ButtonProps) => {
  const sizeClass = buttonSize[size];
  const colorClass = buttonColor[color];

  return (
    <>
      <button className={`flex-center ${sizeClass} ${colorClass}`} disabled={isLoading} onClick={onClick} {...rest}>
        {" "}
        {isLoading ? "Loading.." : children}
      </button>
    </>
  );
};

export default Button;
