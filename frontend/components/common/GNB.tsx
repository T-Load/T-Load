const GNB = ({ isEdit }: { isEdit: boolean }) => {
  return (
    <>
      <div className="bg-green-500 h-[70px]">
        {isEdit ? <div>어우 나 지금 편집중</div> : ""}
        <div>나 GNB야!</div>
      </div>
    </>
  );
};

export default GNB;
